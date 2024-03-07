package com.mizbah.service.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mizbah.dto.SignupRequest;
import com.mizbah.dto.UserDto;
import com.mizbah.entity.Role;
import com.mizbah.entity.User;
import com.mizbah.repository.UserRepository;
import com.mizbah.util.ConversionUtil;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

	UserRepository userRepository;

	PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(SignupRequest signupRequest) {
		User user = ConversionUtil.convert(signupRequest, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = new Role(signupRequest.getRole());
		user.add(role);
		User createdUser = userRepository.save(user);
		UserDto createUserDto = ConversionUtil.convert(createdUser, UserDto.class);
		return createUserDto;
	}

}
