package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.CityDto;
import com.mizbah.dto.RestaurantDto;

public interface RestaurantService {

	List<RestaurantDto> getAllRestaurants();

	RestaurantDto getRestaurantById(long id);

	RestaurantDto createRestaurant(RestaurantDto restaurant);

	RestaurantDto updateRestaurant(long id, RestaurantDto restaurant);

	RestaurantDto updateRestaurant2(long id, RestaurantDto restaurant);

	void deleteRestaurant(long id);

	List<CityDto> getBranchesByRestaurantId(long id);

	List<CityDto> createBranch(long restaurantId, long cityId);

	List<CityDto> deleteBranch(long restaurantId, long cityId);

}
