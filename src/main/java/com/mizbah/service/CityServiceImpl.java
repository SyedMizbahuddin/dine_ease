package com.mizbah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mizbah.adapter.CityAdapter;
import com.mizbah.dto.CityDto;
import com.mizbah.entity.City;
import com.mizbah.repository.CityRepository;
import com.mizbah.service.interfaces.CityService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class CityServiceImpl implements CityService {

	CityRepository cityRepository;
	CityAdapter cityAdapter;

	@Override
	public List<CityDto> getAllCities() {
		return cityAdapter.toDto(cityRepository.findAll());
	}

	@Override
	public CityDto getCityById(long id) {
		Optional<City> city = cityRepository.findById(id);
		if (city.isEmpty()) {
			throw new EntityNotFoundException("City not found with ID: " + id);
		}
		return cityAdapter.toDto(city.get());
	}

	@Override
	public CityDto createCity(CityDto cityRequest) {
		City city = cityAdapter.toEntity(cityRequest);
		cityRepository.save(city);
		return cityAdapter.toDto(city);
	}

	@Override
	public CityDto updateCity(long id, CityDto cityRequest) {

		if (!cityRepository.existsById(id)) {
			throw new EntityNotFoundException("City not found with ID: " + id);
		}

		City city = cityAdapter.toEntity(cityRequest);
		city.setId(id);
		cityRepository.save(city);
		return cityAdapter.toDto(city);

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
