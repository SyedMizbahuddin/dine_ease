package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.CityDto;

public interface CityService {

	List<CityDto> getAllCities();

	CityDto getCityById(long id);

	CityDto createCity(CityDto city);

	CityDto updateCity(long id, CityDto city);

	void deleteCity(long id);

}
