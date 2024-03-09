package com.mizbah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mizbah.dto.FoodCategoryDto;
import com.mizbah.entity.FoodCategory;
import com.mizbah.repository.FoodCategoryRepository;
import com.mizbah.service.interfaces.FoodCategoryService;
import com.mizbah.util.ConversionUtil;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

	FoodCategoryRepository foodCategoryRepository;

	@Override
	public List<FoodCategory> getAllFoodCategories() {
		return foodCategoryRepository.findAll();
	}

	@Override
	public FoodCategory getFoodCategoryById(long id) {
		Optional<FoodCategory> foodCategory = foodCategoryRepository.findById(id);
		if (foodCategory.isEmpty()) {
			throw new EntityNotFoundException("FoodCategory not found with ID: " + id);
		}
		return foodCategory.get();
	}

	@Override
	public FoodCategory createFoodCategory(FoodCategoryDto foodCategoryRequest) {
		FoodCategory foodCategory = ConversionUtil.convert(foodCategoryRequest, FoodCategory.class);
		foodCategoryRepository.save(foodCategory);
		return foodCategory;
	}

	@Override
	public FoodCategory updateFoodCategory(long id, FoodCategoryDto foodCategoryRequest) {

		if (!foodCategoryRepository.existsById(id)) {
			throw new EntityNotFoundException("FoodCategory not found with ID: " + id);
		}

		FoodCategory foodCategory = ConversionUtil.convert(foodCategoryRequest, FoodCategory.class);
		foodCategory.setId(id);
		foodCategoryRepository.save(foodCategory);
		return foodCategory;

	}

	@Override
	public void deleteFoodCategory(long id) {
		if (!foodCategoryRepository.existsById(id)) {
			throw new EntityNotFoundException("FoodCategory not found with ID: " + id);
		}
		foodCategoryRepository.deleteById(id);

	}
}
