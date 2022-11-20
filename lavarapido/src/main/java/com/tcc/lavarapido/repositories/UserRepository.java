package com.tcc.lavarapido.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.lavarapido.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByCpf(String cpf);

	Optional<User> findByEmail(String email);

	Boolean existsByusername(String username);

	Boolean existsByCpf(String cpf);

	Boolean existsByEmail(String email);

}
