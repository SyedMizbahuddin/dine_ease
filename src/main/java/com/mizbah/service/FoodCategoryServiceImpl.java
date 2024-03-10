package com.mizbah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mizbah.adapter.FoodCategoryAdapter;
import com.mizbah.dto.FoodCategoryDto;
import com.mizbah.entity.FoodCategory;
import com.mizbah.repository.FoodCategoryRepository;
import com.mizbah.service.interfaces.FoodCategoryService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

	FoodCategoryRepository foodCategoryRepository;
	FoodCategoryAdapter foodCategoryAdapter;

	@Override
	public List<FoodCategoryDto> getAllFoodCategories() {
		return foodCategoryAdapter.toDto(foodCategoryRepository.findAll());
	}

	@Override
	public FoodCategoryDto getFoodCategoryById(long id) {
		Optional<FoodCategory> foodCategory = foodCategoryRepository.findById(id);
		if (foodCategory.isEmpty()) {
			throw new EntityNotFoundException("FoodCategory not found with ID: " + id);
		}
		return foodCategoryAdapter.toDto(foodCategory.get());
	}

	@Override
	public FoodCategoryDto createFoodCategory(FoodCategoryDto foodCategoryRequest) {
		FoodCategory foodCategory = foodCategoryAdapter.toEntity(foodCategoryRequest);
		foodCategoryRepository.save(foodCategory);
		return foodCategoryAdapter.toDto(foodCategory);
	}

	@Override
	public FoodCategoryDto updateFoodCategory(long id, FoodCategoryDto foodCategoryRequest) {

		if (!foodCategoryRepository.existsById(id)) {
			throw new EntityNotFoundException("FoodCategory not found with ID: " + id);
		}

		FoodCategory foodCategory = foodCategoryAdapter.toEntity(foodCategoryRequest);
		foodCategory.setId(id);
		foodCategoryRepository.save(foodCategory);
		return foodCategoryAdapter.toDto(foodCategory);

	}

	@Override
	public void deleteFoodCategory(long id) {
		if (!foodCategoryRepository.existsById(id)) {
			throw new EntityNotFoundException("FoodCategory not found with ID: " + id);
		}
		// TODO CASCADE on dishes? allow?
		foodCategoryRepository.deleteById(id);

	}
}
