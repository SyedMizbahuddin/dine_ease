package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.DishDto;
import com.mizbah.dto.request.DishRequest;

public interface DishService {

	List<DishDto> getAllDishes();

	DishDto getDishById(long id);

	DishDto createDish(DishRequest dish);

	DishDto updateDish(long id, DishRequest dish);

	void deleteDish(long id);

}
