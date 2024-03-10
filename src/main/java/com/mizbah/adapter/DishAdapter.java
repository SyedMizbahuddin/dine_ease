package com.mizbah.adapter;

import org.springframework.stereotype.Service;

import com.mizbah.dto.DishDto;
import com.mizbah.entity.Dish;

@Service
public class DishAdapter extends AbstractAdapter<Dish, DishDto> {

	@Override
	public Dish toEntity(DishDto dto) {
		return Dish.builder().name(dto.getName()).build();
	}

	@Override
	public DishDto toDto(Dish entity) {
		return DishDto.builder().id(entity.getId()).name(entity.getName()).build();
	}

}
