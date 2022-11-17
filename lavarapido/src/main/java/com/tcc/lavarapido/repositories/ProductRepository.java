package com.tcc.lavarapido.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.lavarapido.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByName(String name);
}
