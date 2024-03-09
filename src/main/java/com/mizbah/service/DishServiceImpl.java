package com.mizbah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mizbah.dto.DishDto;
import com.mizbah.entity.Dish;
import com.mizbah.repository.DishRepository;
import com.mizbah.service.interfaces.DishService;
import com.mizbah.util.ConversionUtil;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class DishServiceImpl implements DishService {

	DishRepository dishRepository;

	@Override
	public List<Dish> getAllDishes() {
		return dishRepository.findAll();
	}

	@Override
	public Dish getDishById(long id) {
		Optional<Dish> dish = dishRepository.findById(id);
		if (dish.isEmpty()) {
			throw new EntityNotFoundException("Dish not found with ID: " + id);
		}
		return dish.get();
	}

	@Override
	public Dish createDish(DishDto dishRequest) {
		Dish dish = ConversionUtil.convert(dishRequest, Dish.class);
		dishRepository.save(dish);
		return dish;
	}

	@Override
	public Dish updateDish(long id, DishDto dishRequest) {

		if (!dishRepository.existsById(id)) {
			throw new EntityNotFoundException("Dish not found with ID: " + id);
		}

		Dish dish = ConversionUtil.convert(dishRequest, Dish.class);
		dish.setId(id);
		dishRepository.save(dish);
		return dish;

	}

	@Override
	public void deleteDish(long id) {
		if (!dishRepository.existsById(id)) {
			throw new EntityNotFoundException("Dish not found with ID: " + id);
		}
		// dependency check to be made, CASCADE
		dishRepository.deleteById(id);

	}
}
