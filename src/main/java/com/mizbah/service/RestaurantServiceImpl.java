package com.mizbah.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mizbah.adapter.CityAdapter;
import com.mizbah.adapter.RestaurantAdapter;
import com.mizbah.dto.CityDto;
import com.mizbah.dto.RestaurantDto;
import com.mizbah.entity.City;
import com.mizbah.entity.Restaurant;
import com.mizbah.entity.User;
import com.mizbah.exception.DependencyException;
import com.mizbah.repository.CityRepository;
import com.mizbah.repository.RestaurantRepository;
import com.mizbah.repository.UserRepository;
import com.mizbah.service.interfaces.RestaurantService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

	RestaurantRepository restaurantRepository;
	UserRepository userRepository;
	CityRepository cityRepository;

	RestaurantAdapter restaurantAdapter;
	CityAdapter cityAdapter;

	@Override
	public List<RestaurantDto> getAllRestaurants() {
		return restaurantAdapter.toDto(restaurantRepository.findAll());
	}

	@Override
	public RestaurantDto getRestaurantById(long id) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		if (restaurant.isEmpty()) {
			throw new EntityNotFoundException("Restaurant not found with ID: " + id);
		}
		return restaurantAdapter.toDto(restaurant.get());
	}

	@Override
	public List<CityDto> getBranchesByRestaurantId(long id) {
		if (!restaurantRepository.existsById(id)) {
			throw new EntityNotFoundException("Restaurant not found with ID: " + id);
		}

		Optional<Restaurant> restaurant = restaurantRepository.getBranchesByRestaurantId(id);
		if (restaurant.isEmpty()) {
			// No branches, not an error
			return new ArrayList<>();
		}
		return cityAdapter.toDto(restaurant.get().getCities());
	}

	@Override
	public RestaurantDto createRestaurant(RestaurantDto restaurantRequest) {

		Optional<User> user = userRepository.findById(restaurantRequest.getOwnerId());

		if (user.isEmpty()) {
			throw new DependencyException("Owner not found with Id: " + restaurantRequest.getOwnerId());
		}

		Restaurant restaurant = restaurantAdapter.toEntity(restaurantRequest);
		restaurant.setOwner(user.get());

		restaurantRepository.save(restaurant);

		return restaurantAdapter.toDto(restaurant);
	}

	@Override
	public List<CityDto> createBranch(long restaurantId, long cityId) {

		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new EntityNotFoundException("Restaurant not found with ID: " + restaurantId));

		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new EntityNotFoundException("City not found with ID: " + cityId));

		restaurant.add(city);
		restaurantRepository.save(restaurant);

		return cityAdapter.toDto(restaurant.getCities());
	}

	@Override
	@Transactional
	public RestaurantDto updateRestaurant(long id, RestaurantDto restaurantRequest) {

		Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				"Restaurant not found with ID: " + id));

		// No Owner change allowed
		restaurant.setName(restaurant.getName() + restaurantRequest.getName());
		restaurant.setId(id);

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		restaurant = restaurantRepository.save(restaurant);

		log.info("restaurant 1 name {}", restaurant.getName());

		return restaurantAdapter.toDto(restaurant);

	}

	@Override
	@Transactional
	public RestaurantDto updateRestaurant2(long id, RestaurantDto restaurantRequest) {

		Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				"Restaurant not found with ID: " + id));

		// No Owner change allowed
		restaurant.setName(restaurant.getName() + restaurantRequest.getName());
		restaurant.setId(id);

		restaurant = restaurantRepository.save(restaurant);

		log.info("restaurant 2 name {}", restaurant.getName());

		return restaurantAdapter.toDto(restaurant);

	}

	@Override
	public void deleteRestaurant(long id) {
		if (!restaurantRepository.existsById(id)) {
			throw new EntityNotFoundException("Restaurant not found with ID: " + id);
		}
		// TODO
		// CASCADE to branches? Yes
		// CASCADE to tables? allow? yes
		// CASCADE to bookings? allow? yes, with notify
		restaurantRepository.deleteById(id);

	}

	@Override
	@Transactional
	public List<CityDto> deleteBranch(long restaurantId, long cityId) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new EntityNotFoundException("Restaurant not found with ID: " + restaurantId));

		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new EntityNotFoundException("City not found with ID: " + cityId));

		if (restaurant.getCities() == null || !restaurant.getCities().contains(city)) {
			throw new DependencyException("No branch with id: " + cityId);
		}
		restaurant.getCities().remove(city);
		restaurantRepository.save(restaurant);

		// TODO
		// CASCADE to tables? allow? yes
		// CASCADE to bookings? allow? yes, with notify
		return cityAdapter.toDto(restaurant.getCities());
	}

}
