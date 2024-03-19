package com.mizbah.service.interfaces;

import com.mizbah.dto.BookingDto;
import com.mizbah.entity.User;

public interface BookingService {

	BookingDto bookTable(long branchTableId, BookingDto bookingRequest, User customer);

	void cancelBooking(long bookingId);

}
