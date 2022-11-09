package com.tcc.lavarapido.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.tcc.lavarapido.repositories.AdminRepository;

public class AdminService {
	
	@Autowired
	final AdminRepository adminRepository;

	public AdminService(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}
	
}
