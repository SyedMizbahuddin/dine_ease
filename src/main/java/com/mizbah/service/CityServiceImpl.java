package com.mizbah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mizbah.dto.CityDto;
import com.mizbah.entity.City;
import com.mizbah.repository.CityRepository;
import com.mizbah.service.interfaces.CityService;
import com.mizbah.util.ConversionUtil;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class CityServiceImpl implements CityService {

	CityRepository cityRepository;

	@Override
	public List<City> getAllCities() {
		return cityRepository.findAll();
	}

	@Override
	public City getCityById(long id) {
		Optional<City> city = cityRepository.findById(id);
		if (city.isEmpty()) {
			throw new EntityNotFoundException("City not found with ID: " + id);
		}
		return city.get();
	}

	@Override
	public City createCity(CityDto cityRequest) {
		City city = ConversionUtil.convert(cityRequest, City.class);
		cityRepository.save(city);
		return city;
	}

	@Override
	public City updateCity(long id, CityDto cityRequest) {

		if (!cityRepository.existsById(id)) {
			throw new EntityNotFoundException("City not found with ID: " + id);
		}

		City city = ConversionUtil.convert(cityRequest, City.class);
		city.setId(id);
		cityRepository.save(city);
		return city;

	}

	@Override
	public void deleteCity(long id) {
		if (!cityRepository.existsById(id)) {
			throw new EntityNotFoundException("City not found with ID: " + id);
		}
		// dependency check to be made, CASCADE
		cityRepository.deleteById(id);

	}
}
