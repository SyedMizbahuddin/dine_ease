package com.mizbah.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizbah.dto.CityDto;
import com.mizbah.dto.RestaurantDto;
import com.mizbah.service.interfaces.BranchService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/restaurants")
@AllArgsConstructor
@RestController
public class BranchController {

	BranchService branchService;

	@GetMapping("/{restaurant_id}/branches")
	ResponseEntity<List<CityDto>> getBranchesByRestaurantId(@PathVariable("restaurant_id") long restaurantId) {
		return ResponseEntity.ok(branchService.getBranchesByRestaurantId(restaurantId));
	}

	@GetMapping("/branches/{city_id}")
	ResponseEntity<List<RestaurantDto>> getRestaurantsByCityId(@PathVariable("city_id") long cityId) {
		return ResponseEntity.ok(branchService.getRestaurantsByCityId(cityId));
	}

	@PostMapping("/{restaurant_id}/branches/{city_id}")
	ResponseEntity<List<CityDto>> createBranch(@PathVariable("restaurant_id") long restaurantId,
			@PathVariable("city_id") long cityId) {
		return ResponseEntity.ok(branchService.createBranch(restaurantId, cityId));
	}

	@DeleteMapping("/{restaurant_id}/branches/{city_id}")
	ResponseEntity<List<CityDto>> deleteBranch(@PathVariable("restaurant_id") long restaurantId,
			@PathVariable("city_id") long cityId) {
		return ResponseEntity.ok(branchService.deleteBranch(restaurantId, cityId));
	}

}
