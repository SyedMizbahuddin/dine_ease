package com.mizbah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mizbah.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
