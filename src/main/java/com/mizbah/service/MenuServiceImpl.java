package com.mizbah.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mizbah.adapter.MenuAdapter;
import com.mizbah.adapter.RestaurantAdapter;
import com.mizbah.dto.MenuDto;
import com.mizbah.dto.RestaurantDto;
import com.mizbah.entity.Dish;
import com.mizbah.entity.Menu;
import com.mizbah.entity.Restaurant;
import com.mizbah.repository.DishRepository;
import com.mizbah.repository.MenuRepository;
import com.mizbah.repository.RestaurantRepository;
import com.mizbah.service.interfaces.MenuService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

	RestaurantRepository restaurantRepository;
	DishRepository dishRepository;
	MenuRepository menuRepository;

	RestaurantAdapter restaurantAdapter;
	MenuAdapter menuAdapter;

	@Override
	public List<MenuDto> getDishesByRestaurantId(long restaurantId) {
		if (!restaurantRepository.existsById(restaurantId)) {
			throw new EntityNotFoundException("Restaurant not found with Id: " + restaurantId);
		}
		List<Menu> menu = menuRepository.findByRestaurantId(restaurantId);
		return menuAdapter.toDto(menu);
	}

	@Override
	public List<RestaurantDto> getRestaurantsByDishId(long dishId) {
		List<Menu> menus = menuRepository.findByDishId(dishId);

		List<Restaurant> restaurants = menus.stream()
				.map(Menu::getRestaurant)
				.collect(Collectors.toList());

		return restaurantAdapter.toDto(restaurants);
	}

	@Transactional
	@Override
	public MenuDto createMenuItem(long restaurantId, long dishId, MenuDto menuRequest) {

		if (menuRepository.existsByRestaurantIdAndDishId(restaurantId, dishId)) {
			throw new EntityExistsException("Menu item already exists with dish Id: " + dishId);
		}

		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new EntityNotFoundException("Restaurant not found with ID: " + restaurantId));

		Dish dish = dishRepository.findById(dishId)
				.orElseThrow(() -> new EntityNotFoundException("Dish not found with ID: " + dishId));

		Menu menu = new Menu();
		menu.setDish(dish);
		menu.setRestaurant(restaurant);
		menu.setPrice(menuRequest.getPrice());

		menuRepository.save(menu);

		return menuAdapter.toDto(menu);
	}

	@Override
	public void deleteMenuItem(long restaurantId, long dishId) {
		if (!menuRepository.existsByRestaurantIdAndDishId(restaurantId, dishId)) {
			throw new EntityNotFoundException("Menu item not found with dish Id: " + dishId);
		}

		Menu menu = menuRepository.findByRestaurantIdAndDishId(restaurantId, dishId);

		menuRepository.delete(menu);
	}

}
