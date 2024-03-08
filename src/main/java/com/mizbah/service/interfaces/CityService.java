package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.CityDto;
import com.mizbah.entity.City;

public interface CityService {

	List<City> getAllCities();

	City getCityById(long id);

	City createCity(CityDto city);

	City updateCity(long id, CityDto city);

	void deleteCity(long id);

}
