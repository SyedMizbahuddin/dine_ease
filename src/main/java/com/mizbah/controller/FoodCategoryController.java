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

import com.mizbah.dto.FoodCategoryDto;
import com.mizbah.entity.FoodCategory;
import com.mizbah.service.interfaces.FoodCategoryService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/food-categories")
@AllArgsConstructor
@RestController
public class FoodCategoryController {

	FoodCategoryService foodCategoryService;

	@GetMapping
	ResponseEntity<List<FoodCategory>> getAllFoodCategories() {
		return ResponseEntity.ok(foodCategoryService.getAllFoodCategories());
	}

	@GetMapping("/{id}")
	ResponseEntity<FoodCategory> getFoodCategoryById(@PathVariable("id") long id) {
		return ResponseEntity.ok(foodCategoryService.getFoodCategoryById(id));
	}

	@PostMapping
	ResponseEntity<FoodCategory> createFoodCategory(@Validated @RequestBody FoodCategoryDto foodCategory) {
		return ResponseEntity.status(HttpStatus.CREATED).body(foodCategoryService.createFoodCategory(foodCategory));
	}

	@PutMapping("/{id}")
	ResponseEntity<FoodCategory> updateFoodCategory(@PathVariable("id") long id,
			@Validated @RequestBody FoodCategoryDto foodCategory) {
		return ResponseEntity.ok(foodCategoryService.updateFoodCategory(id, foodCategory));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteFoodCategory(@PathVariable("id") long id) {
		foodCategoryService.deleteFoodCategory(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
