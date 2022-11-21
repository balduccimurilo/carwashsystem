package com.tcc.lavarapido.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tcc.lavarapido.repositories.UserRepository;

@Service
public class AutenticationService implements UserDetailsService {

	private final UserRepository repository;

	@Autowired
	public AutenticationService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return (UserDetails) repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid data"));
	}
}
