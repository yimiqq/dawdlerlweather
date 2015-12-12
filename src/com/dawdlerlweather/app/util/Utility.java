package com.dawdlerlweather.app.util;

import android.text.TextUtils;

import com.dawdlerlweather.app.db.DawdlerlweatherDB;
import com.dawdlerlweather.app.model.City;
import com.dawdlerlweather.app.model.County;
import com.dawdlerlweather.app.model.Province;

public class Utility {
	/**
	* �����ʹ�����������ص�ʡ������
	*/
	public synchronized static boolean handleProvincesResponse(DawdlerlweatherDB
	dawdlerlWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					// ���������������ݴ洢��Province��
					dawdlerlWeatherDB.saveProvince(province);
				}
				return true;
			}
	}
		return false;
	}
	/**
	* �����ʹ�����������ص��м�����
	*/
	public static boolean handleCitiesResponse(DawdlerlweatherDB dawdlerlWeatherDB,
			String response, int provinceId) {
				if (!TextUtils.isEmpty(response)) {
					String[] allCities = response.split(",");
					if (allCities != null && allCities.length > 0) {
						for (String c : allCities) {
							String[] array = c.split("\\|");
							City city = new City();
							city.setCityCode(array[0]);
							city.setCityName(array[1]);
							city.setProvinceId(provinceId);
							// ���������������ݴ洢��City��
							dawdlerlWeatherDB.saveCity(city);
						}
						return true;
					}
				}
				return false;
	}
	/**
	* �����ʹ�����������ص��ؼ�����
	*/
	public static boolean handleCountiesResponse(DawdlerlweatherDB dawdlerlWeatherDB,
			String response, int cityId) {
				if (!TextUtils.isEmpty(response)) {
					String[] allCounties = response.split(",");
					if (allCounties != null && allCounties.length > 0) {
						for (String c : allCounties) {
							String[] array = c.split("\\|");
							County county = new County();
							county.setCountyCode(array[0]);
							county.setCountyName(array[1]);
							county.setCityId(cityId);
							// ���������������ݴ洢��County��
							dawdlerlWeatherDB.saveCounty(county);
						}
						return true;
					}
				}
				return false;
	}
}
