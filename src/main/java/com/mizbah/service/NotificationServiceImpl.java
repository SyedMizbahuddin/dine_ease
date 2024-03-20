package com.mizbah.service;

import org.springframework.stereotype.Service;

import com.mizbah.entity.Booking;
import com.mizbah.entity.Notification;
import com.mizbah.repository.NotificationRepository;
import com.mizbah.service.interfaces.NotificationService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

	NotificationRepository notificationRepository;

	@Override
	public void notify(Booking booking) {

		Notification notification = new Notification();
		notification.setCustomer(booking.getCustomer());
		notification.setStartDateTime(booking.getStartDateTime());
		notification.setEndDateTime(booking.getEndDateTime());

		notificationRepository.save(notification);
	}

}
