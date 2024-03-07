package com.mizbah.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizbah.dto.SignupRequest;
import com.mizbah.dto.UserDto;
import com.mizbah.service.auth.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

	AuthService authService;

	@PostMapping("/signup")
	ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
		UserDto createdUser = authService.createUser(signupRequest);

		if (createdUser == null) {
			new ResponseEntity<>("User was not created", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}

}
