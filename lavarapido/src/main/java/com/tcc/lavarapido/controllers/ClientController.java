package com.tcc.lavarapido.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcc.lavarapido.models.Client;
import com.tcc.lavarapido.models.dto.ClientDTO;
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
	public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientDTO form, UriComponentsBuilder uriBuilder) {
		
		Client client = clientService.createClient(form);
		
		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId_user()).toUri();
		
		return ResponseEntity.created(uri).body(new ClientDTO(client));
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> listAllClients(@RequestParam (required = false) String name) {
		
		List<Client> response = clientService.findAll();
		
		return ResponseEntity.ok(response);
	}
}
