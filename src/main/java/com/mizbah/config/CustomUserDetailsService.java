package com.mizbah.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mizbah.entity.Role;
import com.mizbah.entity.User;
import com.mizbah.repository.RoleRepository;
import com.mizbah.repository.UserRepository;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with email not found: " + username);
		}

		List<String> roles = new ArrayList<>();
		for (Role role : user.getRoles()) {
			roles.add(role.getRole().name());
		}

		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
				.password(user.getPassword()).roles(roles.toArray(new String[0])).build();
		return userDetails;
	}

}
