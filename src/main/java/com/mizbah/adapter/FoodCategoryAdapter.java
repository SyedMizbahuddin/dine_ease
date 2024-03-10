package com.mizbah.adapter;

import org.springframework.stereotype.Service;

import com.mizbah.dto.FoodCategoryDto;
import com.mizbah.entity.FoodCategory;

@Service
public class FoodCategoryAdapter extends AbstractAdapter<FoodCategory, FoodCategoryDto> {

	@Override
	public FoodCategory toEntity(FoodCategoryDto dto) {
		return FoodCategory.builder().name(dto.getName()).image(dto.getImage()).build();
	}

	@Override
	public FoodCategoryDto toDto(FoodCategory entity) {
		return FoodCategoryDto.builder().id(entity.getId()).name(entity.getName()).image(entity.getImage()).build();
	}

}
