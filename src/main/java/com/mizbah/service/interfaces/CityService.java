package com.mizbah.service.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mizbah.dto.CityDto;

public interface CityService {

	List<CityDto> getAllCities();

	CityDto getCityById(long id);

	CityDto createCity(CityDto city);

	@Transactional
	CityDto updateCity(long id, CityDto city);

	void deleteCity(long id);

}
