package com.mizbah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mizbah.adapter.DishAdapter;
import com.mizbah.dto.DishDto;
import com.mizbah.entity.Dish;
import com.mizbah.entity.FoodCategory;
import com.mizbah.exception.DependencyException;
import com.mizbah.repository.DishRepository;
import com.mizbah.repository.FoodCategoryRepository;
import com.mizbah.service.interfaces.DishService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class DishServiceImpl implements DishService {

	DishRepository dishRepository;
	FoodCategoryRepository foodCategoryRepository;
	DishAdapter dishAdapter;

	@Override
	public List<DishDto> getAllDishes() {
		return dishAdapter.toDto(dishRepository.findAll());
	}

	@Override
	public DishDto getDishById(long id) {
		Optional<Dish> dish = dishRepository.findById(id);
		if (dish.isEmpty()) {
			throw new EntityNotFoundException("Dish not found with ID: " + id);
		}
		return dishAdapter.toDto(dish.get());
	}

	@Override
	public DishDto createDish(DishDto dishRequest) {

		Optional<FoodCategory> foodCategory = foodCategoryRepository.findById(dishRequest.getCategoryId());

		if (foodCategory.isEmpty()) {
			throw new DependencyException("Food category not found with Id: " + dishRequest.getCategoryId());
		}

		Dish dish = dishAdapter.toEntity(dishRequest);
		dish.setCategory(foodCategory.get());

		dishRepository.save(dish);

		return dishAdapter.toDto(dish);
	}

	@Override
	public DishDto updateDish(long id, DishDto dishRequest) {

		if (!dishRepository.existsById(id)) {
			throw new EntityNotFoundException("Dish not found with ID: " + id);
		}

		Optional<FoodCategory> foodCategory = foodCategoryRepository.findById(dishRequest.getCategoryId());

		if (foodCategory.isEmpty()) {
			throw new DependencyException("Food category not found with Id: " + dishRequest.getCategoryId());
		}

		Dish dish = dishAdapter.toEntity(dishRequest);
		dish.setCategory(foodCategory.get());
		dish.setId(id);

		dishRepository.save(dish);

		return dishAdapter.toDto(dish);

	}

	@Override
	public void deleteDish(long id) {
		if (!dishRepository.existsById(id)) {
			throw new EntityNotFoundException("Dish not found with ID: " + id);
		}
		// CASCADE to category? No
		// TODO CASCADE to menu? allow? yes TODO
		dishRepository.deleteById(id);

	}
}
