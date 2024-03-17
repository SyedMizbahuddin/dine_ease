package com.mizbah.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mizbah.adapter.CityAdapter;
import com.mizbah.adapter.RestaurantAdapter;
import com.mizbah.dto.CityDto;
import com.mizbah.entity.City;
import com.mizbah.entity.Restaurant;
import com.mizbah.exception.DependencyException;
import com.mizbah.repository.CityRepository;
import com.mizbah.repository.RestaurantRepository;
import com.mizbah.service.interfaces.BranchService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class BranchServiceImpl implements BranchService {

	RestaurantRepository restaurantRepository;
	CityRepository cityRepository;

	RestaurantAdapter restaurantAdapter;
	CityAdapter cityAdapter;

	@Override
	public List<CityDto> getBranchesByRestaurantId(long restaurantId) {
		if (!restaurantRepository.existsById(restaurantId)) {
			throw new EntityNotFoundException("Restaurant not found with ID: " + restaurantId);
		}

		Optional<Restaurant> restaurant = restaurantRepository.getBranchesByRestaurantId(restaurantId);
		if (restaurant.isEmpty()) {
			// No branches, not an error
			return new ArrayList<>();
		}
		return cityAdapter.toDto(restaurant.get().getCities());
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
