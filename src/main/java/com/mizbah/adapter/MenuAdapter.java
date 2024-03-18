package com.mizbah.adapter;

import org.springframework.stereotype.Service;

import com.mizbah.adapter.interfaces.AbstractAdapter;
import com.mizbah.dto.MenuDto;
import com.mizbah.entity.Menu;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MenuAdapter extends AbstractAdapter<Menu, MenuDto> {

	DishAdapter dishAdapter;
	FoodCategoryAdapter foodCategoryAdapter;

	@Override
	public Menu toEntity(MenuDto dto) {
		return Menu.builder().price(dto.getPrice()).build();
	}

	@Override
	public MenuDto toDto(Menu entity) {
		return MenuDto.builder()
				.id(entity.getId())
				.price(entity.getPrice())
				.dish(dishAdapter.toDto(entity.getDish()))
				.foodCategory(foodCategoryAdapter.toDto(entity.getDish().getCategory()))
				.build();

	}

}
