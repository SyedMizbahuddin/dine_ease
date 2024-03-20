package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.RestaurantDto;
import com.mizbah.dto.request.RestaurantRequest;
import com.mizbah.entity.User;

public interface RestaurantService {

	List<RestaurantDto> getAllRestaurants();

	RestaurantDto getRestaurantById(long id);

	RestaurantDto createRestaurant(RestaurantRequest restaurant, User owner);

	RestaurantDto updateRestaurant(long id, RestaurantRequest restaurant);

	void deleteRestaurant(long id);

	List<RestaurantDto> getRestaurantByName(String name);

}
