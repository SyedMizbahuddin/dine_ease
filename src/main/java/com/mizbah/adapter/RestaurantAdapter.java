package com.mizbah.adapter;

import org.springframework.stereotype.Service;

import com.mizbah.dto.RestaurantDto;
import com.mizbah.entity.Restaurant;

@Service
public class RestaurantAdapter extends AbstractAdapter<Restaurant, RestaurantDto> {

	@Override
	public Restaurant toEntity(RestaurantDto dto) {
		return Restaurant.builder().name(dto.getName()).image(dto.getImage()).build();
	}

	@Override
	public RestaurantDto toDto(Restaurant entity) {
		return RestaurantDto.builder().id(entity.getId()).name(entity.getName()).image(entity.getImage()).build();
	}

}
