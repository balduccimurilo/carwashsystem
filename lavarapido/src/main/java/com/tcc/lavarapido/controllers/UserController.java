package com.tcc.lavarapido.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcc.lavarapido.models.User;
import com.tcc.lavarapido.models.dto.UserDTO;
import com.tcc.lavarapido.services.AdminService;
import com.tcc.lavarapido.services.ClientService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private AdminService adminService;
	private ClientService clientService;
	
	@Autowired
	public UserController(AdminService adminService, ClientService clientService) {
		this.adminService = adminService;
		this.clientService = clientService;
	}
	
//	@PostMapping("/admin")
//	public ResponseEntity<UserDTO> createAdmin(@RequestBody @Valid UserDTO form, UriComponentsBuilder uriBuilder) {
//		
//		User admin = adminService.createAdmin(form);
//		
//		URI uri = uriBuilder.path("/admin/{id}").buildAndExpand(admin.getId_user()).toUri();
//		
//		return ResponseEntity.created(uri).body(new UserDTO(admin));
//	}
	
	@GetMapping("/admin")
	public ResponseEntity<List<User>> listAllAdmin(@RequestParam (required = false) String name) {
		
		List<User> response = adminService.findAll();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<User> findOneAdmin(@PathVariable(value = "id") Long id) {
		
		User admin = adminService.findById(id);
		
		return ResponseEntity.ok(admin);		
	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<Object> deleteAdminById(@PathVariable(value = "id") Long id) {
		
		adminService.delete(id);
		
		return ResponseEntity.ok().build();		
	}
	
	// Client 
	
//	@PostMapping("/client")
//	public ResponseEntity<UserDTO> createClient(@RequestBody @Valid UserDTO form, UriComponentsBuilder uriBuilder) {
//		
//		User client = clientService.createClient(form);
//		
//		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId_user()).toUri();
//		
//		return ResponseEntity.created(uri).body(new UserDTO(client));
//	}
	
	@GetMapping("/client")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public ResponseEntity<List<User>> listAllClients(@RequestParam (required = false) String name) {
		
		List<User> response = clientService.findAll();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/client/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
	public ResponseEntity<User> findOneClient(@PathVariable(value = "id") Long id) {
		
		User client = clientService.findById(id);
		
		return ResponseEntity.ok(client);		
	}
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<Object> deleteClientById(@PathVariable(value = "id") Long id) {
		
		clientService.delete(id);
		
		return ResponseEntity.ok().build();		
	}
	
}
