package com.tcc.lavarapido.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcc.lavarapido.forms.WashForm;
import com.tcc.lavarapido.models.Wash;
import com.tcc.lavarapido.models.dto.WashDTO;
import com.tcc.lavarapido.services.WashService;

@RestController
@RequestMapping("/wash")
public class WashController {
	
	@Autowired
	WashService washService;

	@Autowired
	public WashController(WashService washService) {
		super();
		this.washService = washService;
	}
	
	@PostMapping
	public ResponseEntity<WashDTO> createWash(@RequestBody @Valid WashForm form, UriComponentsBuilder uriBuilder) {
		
		Wash wash = washService.createWash(form);
		
		URI uri = uriBuilder.path("/wash/{id}").buildAndExpand(wash.getIdWash()).toUri();
		
		return ResponseEntity.created(uri).body(new WashDTO(wash));
	}
	
}
