package com.mizbah.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mizbah.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
