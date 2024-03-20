package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.BookingDto;
import com.mizbah.dto.request.BookingRequest;
import com.mizbah.entity.User;

public interface BookingService {

	BookingDto bookTable(long branchTableId, BookingRequest bookingRequest, User customer);

	void cancelBooking(long bookingId);

	List<BookingDto> getBookings(User customer);

	List<BookingDto> getBookingsByRestaurantId(long restaurantId);

}
