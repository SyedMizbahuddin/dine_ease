package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.MenuDto;
import com.mizbah.dto.RestaurantDto;
import com.mizbah.dto.request.MenuRequest;

public interface MenuService {

	MenuDto createMenuItem(long restaurantId, long dishId, MenuRequest menuRequest);

	List<MenuDto> getDishesByRestaurantId(long restaurantId);

	void deleteMenuItem(long menuId);

	List<RestaurantDto> getRestaurantsByDishId(long dishId);

}
