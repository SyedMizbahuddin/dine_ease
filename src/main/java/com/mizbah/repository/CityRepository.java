package com.mizbah.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mizbah.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
