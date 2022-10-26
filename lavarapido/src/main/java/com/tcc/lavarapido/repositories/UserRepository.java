package com.tcc.lavarapido.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.lavarapido.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	List<User> findByName(String name);
	Optional<User> findByEmail(String email);

}
