package com.mizbah.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuDto {

	private Long id;
	private int price;
	private DishDto dish;
	private FoodCategoryDto foodCategory;
}
