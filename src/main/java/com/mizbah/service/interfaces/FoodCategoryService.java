package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.FoodCategoryDto;

public interface FoodCategoryService {

	List<FoodCategoryDto> getAllFoodCategories();

	FoodCategoryDto getFoodCategoryById(long id);

	FoodCategoryDto createFoodCategory(FoodCategoryDto foodCategory);

	FoodCategoryDto updateFoodCategory(long id, FoodCategoryDto foodCategory);

	void deleteFoodCategory(long id);

}
