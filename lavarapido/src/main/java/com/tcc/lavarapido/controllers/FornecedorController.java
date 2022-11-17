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

import com.tcc.lavarapido.models.Fornecedor;
import com.tcc.lavarapido.models.dto.FornecedorDTO;
import com.tcc.lavarapido.services.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

	@Autowired
	FornecedorService fornecedorService;

	@Autowired
	public FornecedorController(FornecedorService fornecedorService) {
		super();
		this.fornecedorService = fornecedorService;
	}

	@PostMapping
	public ResponseEntity<FornecedorDTO> createForncedor(@RequestBody @Valid FornecedorDTO form,
			UriComponentsBuilder uriBuilder) {

		Fornecedor fornecedor = fornecedorService.createFornecedor(form);

		URI uri = uriBuilder.path("/wash/{id}").buildAndExpand(fornecedor.getIdFornecedor()).toUri();

		return ResponseEntity.created(uri).body(new FornecedorDTO(fornecedor));
	}

	@GetMapping
	public ResponseEntity<List<Fornecedor>> listAllFornecedores(@RequestParam(required = false) String name) {

		List<Fornecedor> response = fornecedorService.findAll();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> findOneFornecedor(@PathVariable(value = "id") Long id) {

		Fornecedor fornecedor = fornecedorService.findById(id);

		return ResponseEntity.ok(fornecedor);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {

		fornecedorService.delete(id);

		return ResponseEntity.ok().build();
	}

}
