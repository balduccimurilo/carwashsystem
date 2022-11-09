package com.tcc.lavarapido.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.tcc.lavarapido.repositories.WashRepository;

public class WashService {
	
	@Autowired
	WashRepository washRepository;

	public WashService(WashRepository washRepository) {
		super();
		this.washRepository = washRepository;
	}

}
