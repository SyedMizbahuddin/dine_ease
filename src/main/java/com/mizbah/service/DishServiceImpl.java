package com.mizbah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mizbah.adapter.DishAdapter;
import com.mizbah.dto.DishDto;
import com.mizbah.dto.request.DishRequest;
import com.mizbah.entity.Dish;
import com.mizbah.entity.FoodCategory;
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
	public DishDto createDish(DishRequest dishRequest) {

		FoodCategory foodCategory = foodCategoryRepository.findById(dishRequest.getCategoryId()).orElseThrow(
				() -> new EntityNotFoundException(
						"FoodCategory not found with ID: " + dishRequest.getCategoryId()));

		Dish dish = new Dish();
		dish.setCategory(foodCategory);
		dish.setName(dishRequest.getName());

		dishRepository.save(dish);

		return dishAdapter.toDto(dish);
	}

	@Transactional
	@Override
	public DishDto updateDish(long id, DishRequest dishRequest) {

		Dish dish = dishRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Dish not found with ID: " + id));

		FoodCategory foodCategory = foodCategoryRepository.findById(dishRequest.getCategoryId())
				.orElseThrow(() -> new EntityNotFoundException(
						"FoodCategory not found with ID: " + dishRequest.getCategoryId()));

		dish.setCategory(foodCategory);
		dish.setName(dishRequest.getName());

		dishRepository.save(dish);

		return dishAdapter.toDto(dish);

	}

	@Transactional
	@Override
	public void deleteDish(long id) {
		if (!dishRepository.existsById(id)) {
			throw new EntityNotFoundException("Dish not found with ID: " + id);
		}

		// Cascades to menu
		dishRepository.deleteById(id);

	}
}
