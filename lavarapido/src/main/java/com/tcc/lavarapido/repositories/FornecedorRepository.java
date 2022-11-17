package com.tcc.lavarapido.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.lavarapido.models.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
	
	Optional<Fornecedor> findByCnpj(String cnpj);
	Optional<Fornecedor> findByFantasyName(String fantasyName);
}
