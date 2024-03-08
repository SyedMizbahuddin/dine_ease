package com.mizbah.service.interfaces;

import com.mizbah.dto.LoginRequest;
import com.mizbah.dto.LoginResponse;
import com.mizbah.dto.SignupRequest;
import com.mizbah.dto.UserDto;

public interface AuthService {

	UserDto createUser(SignupRequest request);

	LoginResponse login(LoginRequest request);

}
