package com.mizbah.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizbah.dto.CityDto;
import com.mizbah.dto.RestaurantDto;
import com.mizbah.service.interfaces.RestaurantService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/restaurants")
@AllArgsConstructor
@RestController
public class RestaurantController {

	RestaurantService restaurantService;

	@GetMapping
	ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
		return ResponseEntity.ok(restaurantService.getAllRestaurants());
	}

	@GetMapping("/{id}")
	ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable("id") long id) {
		return ResponseEntity.ok(restaurantService.getRestaurantById(id));
	}

	@GetMapping("/{id}/branches")
	ResponseEntity<List<CityDto>> getBranchesByRestaurantId(@PathVariable("id") long id) {
		return ResponseEntity.ok(restaurantService.getBranchesByRestaurantId(id));
	}

	@PostMapping("/{restaurant_id}/branches/{city_id}")
	ResponseEntity<List<CityDto>> createBranch(@PathVariable("restaurant_id") long restaurantId,
			@PathVariable("city_id") long cityId) {
		return ResponseEntity.ok(restaurantService.createBranch(restaurantId, cityId));
	}

	@PostMapping
	ResponseEntity<RestaurantDto> createRestaurant(@Validated @RequestBody RestaurantDto restaurant) {
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createRestaurant(restaurant));
	}

	@PutMapping("/{id}")
	ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable("id") long id,
			@Validated @RequestBody RestaurantDto restaurant) {
		return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurant));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteRestaurant(@PathVariable("id") long id) {
		restaurantService.deleteRestaurant(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{restaurant_id}/branches/{city_id}")
	ResponseEntity<List<CityDto>> deleteBranch(@PathVariable("restaurant_id") long restaurantId,
			@PathVariable("city_id") long cityId) {
		return ResponseEntity.ok(restaurantService.deleteBranch(restaurantId, cityId));
	}
}
