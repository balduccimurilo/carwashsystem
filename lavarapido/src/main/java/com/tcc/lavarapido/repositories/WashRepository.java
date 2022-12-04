package com.tcc.lavarapido.repositories;


import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.lavarapido.models.Wash;

public interface WashRepository extends JpaRepository<Wash, Long>{
	
	Boolean existsBydtReservation (LocalDateTime dtReservation);
}
