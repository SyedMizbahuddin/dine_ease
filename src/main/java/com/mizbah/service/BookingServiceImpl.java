package com.mizbah.service;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mizbah.adapter.BookingAdapter;
import com.mizbah.dto.BookingDto;
import com.mizbah.entity.Booking;
import com.mizbah.entity.User;
import com.mizbah.repository.BookingRepository;
import com.mizbah.repository.BranchTableRepository;
import com.mizbah.service.interfaces.BookingService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

	BookingRepository bookingRepository;
	BranchTableRepository branchTableRepository;

	BookingAdapter bookingAdapter;

	@Transactional
	@Override
	public BookingDto bookTable(long branchTableId, BookingDto bookingRequest, User customer) {

		Booking booking = bookingAdapter.toEntity(bookingRequest);
		Date start = booking.getStartDateTime();
		Date end = DateUtils.addHours(start, 1);

		return null;
	}

}
