package com.mizbah.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mizbah.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
