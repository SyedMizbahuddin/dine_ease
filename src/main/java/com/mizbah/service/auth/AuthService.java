package com.mizbah.service.auth;

import com.mizbah.dto.LoginRequest;
import com.mizbah.dto.SignupRequest;
import com.mizbah.dto.UserDto;

public interface AuthService {

	UserDto createUser(SignupRequest request);

	UserDto login(LoginRequest request);

}
