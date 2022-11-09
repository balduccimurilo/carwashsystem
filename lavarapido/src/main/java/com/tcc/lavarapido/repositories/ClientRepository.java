package com.tcc.lavarapido.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.lavarapido.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Optional<Client> findByEmail(String email);
	Optional<Client> findByCpf(String cpf);
}
