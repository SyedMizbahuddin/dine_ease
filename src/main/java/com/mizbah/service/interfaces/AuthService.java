package com.mizbah.service.interfaces;

import com.mizbah.dto.LoginResponse;
import com.mizbah.dto.UserDto;
import com.mizbah.dto.request.LoginRequest;
import com.mizbah.dto.request.SignupRequest;

public interface AuthService {

	UserDto createUser(SignupRequest request);

	LoginResponse login(LoginRequest request);

}
