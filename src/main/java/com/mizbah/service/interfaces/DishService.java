package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.DishDto;
import com.mizbah.entity.Dish;

public interface DishService {

	List<Dish> getAllDishes();

	Dish getDishById(long id);

	Dish createDish(DishDto dish);

	Dish updateDish(long id, DishDto dish);

	void deleteDish(long id);

}
