package com.mizbah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mizbah.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
