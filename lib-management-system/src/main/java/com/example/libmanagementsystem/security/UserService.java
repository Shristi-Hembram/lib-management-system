package com.example.libmanagementsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

}
