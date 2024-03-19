package com.mizbah.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizbah.dto.BookingDto;
import com.mizbah.entity.User;
import com.mizbah.service.interfaces.BookingService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/bookings")
@AllArgsConstructor
@RestController
public class BookingController {

	BookingService bookingService;

	@PostMapping("/{branch_table_id}")
	ResponseEntity<BookingDto> bookTable(@PathVariable("branch_table_id") long branchTableId,
			@Valid @RequestBody BookingDto bookingRequest,
			Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return ResponseEntity.ok(bookingService.bookTable(branchTableId, bookingRequest, user));
	}

	@DeleteMapping("/{booking_id}")
	ResponseEntity<?> cancelBooking(@PathVariable("booking_id") long bookingId) {
		bookingService.cancelBooking(bookingId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
