package com.mizbah.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mizbah.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	@Query("Select r FROM Restaurant r JOIN FETCH r.cities cities WHERE r.id=:id")
	public Optional<Restaurant> getBranchesByRestaurantId(long id);

	public List<Restaurant> findByNameContaining(String name);

}
