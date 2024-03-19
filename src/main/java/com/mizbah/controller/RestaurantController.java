package com.mizbah.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mizbah.dto.RestaurantDto;
import com.mizbah.entity.User;
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

	@GetMapping("/name")
	ResponseEntity<List<RestaurantDto>> getRestaurantByName(@RequestParam("name") String name) {
		return ResponseEntity.ok(restaurantService.getRestaurantByName(name));
	}

	@PostMapping
	ResponseEntity<RestaurantDto> createRestaurant(@Validated @RequestBody RestaurantDto restaurant,
			Authentication authentication) {
		User user = (User) authentication.getPrincipal();

		return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createRestaurant(restaurant, user
				.getId()));
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

}
