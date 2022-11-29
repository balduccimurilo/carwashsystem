package com.tcc.lavarapido.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcc.lavarapido.forms.ProductForm;
import com.tcc.lavarapido.models.Product;
import com.tcc.lavarapido.models.dto.ProductDTO;
import com.tcc.lavarapido.services.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductForm form,
			UriComponentsBuilder uriBuilder) {

		Product product = productService.createProduct(form);

		URI uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getIdProduct()).toUri();

		return ResponseEntity.created(uri).body(new ProductDTO(product));
	}

	@GetMapping
	public ResponseEntity<List<Product>> listAllFornecedores(@RequestParam(required = false) String name) {

		List<Product> response = productService.findAll();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> findOneProduct(@PathVariable(value = "id") Long id) {

		Product product = productService.findById(id);

		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {

		productService.delete(id);

		return ResponseEntity.ok().build();
	}
	
	
}
