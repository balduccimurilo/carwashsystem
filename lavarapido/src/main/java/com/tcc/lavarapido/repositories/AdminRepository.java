package com.tcc.lavarapido.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.lavarapido.models.Admin;
import com.tcc.lavarapido.models.Client;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin save(Admin newAdmin);

	Optional<Client> findByCpf(String cpf);

	Optional<Client> findByEmail(String email);

}
