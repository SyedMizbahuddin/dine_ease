package com.mizbah.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizbah.dto.CityDto;
import com.mizbah.entity.City;
import com.mizbah.service.interfaces.CityService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/cities")
@AllArgsConstructor
@RestController
public class CityController {

	CityService cityService;

	@GetMapping
	ResponseEntity<List<City>> getAllCities() {
		return ResponseEntity.ok(cityService.getAllCities());
	}

	@GetMapping("/{id}")
	ResponseEntity<City> getCityById(@PathVariable("id") long id) {
		return ResponseEntity.ok(cityService.getCityById(id));
	}

	@PostMapping
	ResponseEntity<City> createCity(@RequestBody CityDto city) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cityService.createCity(city));
	}

	@PutMapping("/{id}")
	ResponseEntity<City> updateCity(@PathVariable("id") long id, @RequestBody CityDto city) {
		return ResponseEntity.ok(cityService.updateCity(id, city));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteCity(@PathVariable("id") long id) {
		cityService.deleteCity(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
