package com.tcc.lavarapido.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.lavarapido.models.User;
import com.tcc.lavarapido.models.Wash;

public interface WashRepository extends JpaRepository<Wash, UUID> {
	
	
}
