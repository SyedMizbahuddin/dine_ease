package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.DishDto;

public interface DishService {

	List<DishDto> getAllDishes();

	DishDto getDishById(long id);

	DishDto createDish(DishDto dish);

	DishDto updateDish(long id, DishDto dish);

	void deleteDish(long id);

}
