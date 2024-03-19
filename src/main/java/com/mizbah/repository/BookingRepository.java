package com.mizbah.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mizbah.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	boolean existsByBranchTableIdAndStartDateTime(Long branchTableId, Date startDateTime);
}
