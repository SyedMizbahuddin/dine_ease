package com.mizbah.service;

import com.mizbah.entity.Booking;
import com.mizbah.service.interfaces.NotificationService;
import com.mizbah.util.BeanContextUtil;

import jakarta.persistence.PostRemove;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class BookingEntityListener {

	@PostRemove
	public void preRemove(Booking booking) {
		NotificationService notificationService = BeanContextUtil.getBean(NotificationService.class);
		notificationService.notify(booking);

	}

}