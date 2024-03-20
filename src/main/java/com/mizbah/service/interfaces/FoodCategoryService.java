package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.FoodCategoryDto;
import com.mizbah.dto.request.FoodCategoryRequest;

public interface FoodCategoryService {

	List<FoodCategoryDto> getAllFoodCategories();

	FoodCategoryDto getFoodCategoryById(long id);

	FoodCategoryDto createFoodCategory(FoodCategoryRequest foodCategory);

	FoodCategoryDto updateFoodCategory(long id, FoodCategoryRequest foodCategory);

	void deleteFoodCategory(long id);

}
