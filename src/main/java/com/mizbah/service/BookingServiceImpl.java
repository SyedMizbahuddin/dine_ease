package com.mizbah.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mizbah.adapter.BookingAdapter;
import com.mizbah.dto.BookingDto;
import com.mizbah.dto.request.BookingRequest;
import com.mizbah.entity.Booking;
import com.mizbah.entity.BranchTable;
import com.mizbah.entity.User;
import com.mizbah.repository.BookingRepository;
import com.mizbah.repository.BranchTableRepository;
import com.mizbah.service.interfaces.BookingService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

	BookingRepository bookingRepository;
	BranchTableRepository branchTableRepository;

	BookingAdapter bookingAdapter;

	@Override
	public List<BookingDto> getBookings(User customer) {
		List<Booking> bookings = bookingRepository.findByCustomerId(customer.getId());
		return bookingAdapter.toDto(bookings);
	}

	@Override
	public List<BookingDto> getBookingsByRestaurantId(long restaurantId) {
		List<Booking> bookings = bookingRepository.findByRestaurantId(restaurantId);
		return bookingAdapter.toDto(bookings);
	}

	@Transactional
	@Override
	public BookingDto bookTable(long branchTableId, BookingRequest bookingRequest, User customer) {

		if (bookingRepository.existsByBranchTableIdAndStartDateTime(branchTableId, bookingRequest.getStartDateTime())) {
			throw new EntityExistsException("Table already booked at requested time");
		}

		BranchTable branchTable = branchTableRepository.findById(branchTableId).orElseThrow(
				() -> new EntityNotFoundException("Branch table not found with Id: " + branchTableId));

		Booking booking = new Booking();
		Date start = bookingRequest.getStartDateTime();
		Date end = DateUtils.addHours(start, 1);

		booking.setEndDateTime(end);
		booking.setStartDateTime(start);
		booking.setCustomer(customer);
		booking.setBranchTable(branchTable);

		bookingRepository.save(booking);

		return bookingAdapter.toDto(booking);
	}

	@Transactional
	@Override
	public void cancelBooking(long bookingId) {

		if (!bookingRepository.existsById(bookingId)) {
			throw new EntityNotFoundException("Booking not found with Id: " + bookingId);
		}

		bookingRepository.deleteById(bookingId);
	}

}
