package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.FoodCategoryDto;
import com.mizbah.entity.FoodCategory;

public interface FoodCategoryService {

	List<FoodCategory> getAllFoodCategories();

	FoodCategory getFoodCategoryById(long id);

	FoodCategory createFoodCategory(FoodCategoryDto foodCategory);

	FoodCategory updateFoodCategory(long id, FoodCategoryDto foodCategory);

	void deleteFoodCategory(long id);

}
