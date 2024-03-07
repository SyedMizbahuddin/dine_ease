package com.mizbah.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mizbah.entity.Role;
import com.mizbah.entity.User;
import com.mizbah.repository.UserRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with email not found: " + username);
		}

		List<GrantedAuthority> roles = new ArrayList<>();
		for (Role role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role.getRole().name()));
		}

		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
				.password(user.getPassword()).authorities(roles).build();
		return userDetails;
	}

}
