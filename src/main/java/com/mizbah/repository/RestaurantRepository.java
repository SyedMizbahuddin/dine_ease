package com.mizbah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mizbah.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	public List<Restaurant> findByNameContaining(String name);

}
