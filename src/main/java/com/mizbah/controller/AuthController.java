package com.mizbah.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizbah.dto.LoginRequest;
import com.mizbah.dto.LoginResponse;
import com.mizbah.dto.SignupRequest;
import com.mizbah.dto.UserDto;
import com.mizbah.service.interfaces.AuthService;

import lombok.AllArgsConstructor;

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

	@PostMapping("/login")
	ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		LoginResponse response = authService.login(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
