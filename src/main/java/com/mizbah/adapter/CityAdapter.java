package com.mizbah.adapter;

import org.springframework.stereotype.Service;

import com.mizbah.dto.CityDto;
import com.mizbah.entity.City;

@Service
public class CityAdapter extends AbstractAdapter<City, CityDto> {

	@Override
	public City toEntity(CityDto dto) {
		return City.builder().city(dto.getCity()).build();
	}

	@Override
	public CityDto toDto(City entity) {
		return CityDto.builder().id(entity.getId()).city(entity.getCity()).build();
	}

}
