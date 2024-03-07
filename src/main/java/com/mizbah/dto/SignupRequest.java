package com.mizbah.dto;

import com.mizbah.enums.UserRole;

import lombok.Data;

@Data
public class SignupRequest {

	private String name;
	private String email;
	private String password;
	private UserRole role;
}
