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

import com.tcc.lavarapido.models.Client;
import com.tcc.lavarapido.models.dto.UserDTO;
import com.tcc.lavarapido.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	private ClientService clientService;
	
	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createClient(@RequestBody @Valid UserDTO form, UriComponentsBuilder uriBuilder) {
		
		Client client = clientService.createClient(form);
		
		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId_user()).toUri();
		
		return ResponseEntity.created(uri).body(new UserDTO(client));
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> listAllClients(@RequestParam (required = false) String name) {
		
		List<Client> response = clientService.findAll();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findOneClient(@PathVariable(value = "id") Long id) {
		
		Client client = clientService.findById(id);
		
		return ResponseEntity.ok(client);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
		
		clientService.delete(id);
		
		return ResponseEntity.ok().build();		
	}
}
