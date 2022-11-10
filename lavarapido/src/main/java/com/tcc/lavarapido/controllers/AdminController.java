package com.tcc.lavarapido.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcc.lavarapido.models.Admin;
import com.tcc.lavarapido.models.dto.UserDTO;
import com.tcc.lavarapido.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createAdmin(@RequestBody @Valid UserDTO form, UriComponentsBuilder uriBuilder) {
		
		Admin admin = adminService.createAdmin(form);
		
		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(admin.getId_user()).toUri();
		
		return ResponseEntity.created(uri).body(new UserDTO(admin));
	}
	
	@GetMapping
	public ResponseEntity<List<Admin>> listAllClients(@RequestParam (required = false) String name) {
		
		List<Admin> response = adminService.findAll();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Admin> findOneClient(@PathVariable(value = "id") Long id) {
		
		Admin admin = adminService.findById(id);
		
		return ResponseEntity.ok(admin);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
		
		adminService.delete(id);
		
		return ResponseEntity.ok().build();		
	}
	
}
