package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.MenuDto;

public interface MenuService {

	MenuDto createMenuItem(long restaurantId, long dishId, MenuDto menuRequest);

	List<MenuDto> getDishesByRestaurantId(long restaurantId);

	void deleteMenuItem(long restaurantId, long dishId);

}
