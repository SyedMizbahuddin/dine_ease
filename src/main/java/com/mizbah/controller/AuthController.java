package com.mizbah.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizbah.dto.LoginRequest;
import com.mizbah.dto.SignupRequest;
import com.mizbah.dto.UserDto;
import com.mizbah.service.auth.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

	AuthService authService;

	@PostMapping("/signup")
	ResponseEntity<UserDto> signup(@RequestBody SignupRequest request) {
		UserDto createdUser = authService.createUser(request);
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}

	@PostMapping("/lgin")
	ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
		UserDto createdUser = authService.login(request);
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}

}
