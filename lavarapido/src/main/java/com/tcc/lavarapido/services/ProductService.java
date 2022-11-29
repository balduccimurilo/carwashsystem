package com.tcc.lavarapido.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.lavarapido.exceptions.ProductException;
import com.tcc.lavarapido.forms.ProductForm;
import com.tcc.lavarapido.models.Fornecedor;
import com.tcc.lavarapido.models.Product;
import com.tcc.lavarapido.models.dto.ProductDTO;
import com.tcc.lavarapido.repositories.FornecedorRepository;
import com.tcc.lavarapido.repositories.ProductRepository;

@Service
public class ProductService {
	
	public static final String PRODUCT_NOT_FOUND = "There isn't a product with id = ";
	public static final String PRODUCT_NOT_FOUND_NAME = "There isn't a product with name = ";
	
	public static final String FORNECEDOR_NOT_FOUND = "There isn't a fornecedor with id = ";
	
	
	final ProductRepository productRepository;
	final FornecedorRepository fornecedorRepository;
	final FornecedorService fornecedorService;

	@Autowired
	public ProductService(ProductRepository productRepository, FornecedorRepository fornecedorRepository, FornecedorService fornecedorService) {
		super();
		this.productRepository = productRepository;
		this.fornecedorRepository = fornecedorRepository;
		this.fornecedorService = fornecedorService;
	}
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductException(PRODUCT_NOT_FOUND + id, HttpStatus.NOT_FOUND));
	}
	
	public Product findByName(String name) {
		return productRepository.findByName(name)
				.orElseThrow(() -> new ProductException(PRODUCT_NOT_FOUND_NAME + name, HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public Product createProduct(@Valid ProductForm form) {
			
		ProductDTO dto = new ProductDTO();

		Product newProduct = null;

		try {

			dto.setId(null);
			
			Fornecedor fornecedor = fornecedorService.findById(form.getFornecedor_id());
			if(fornecedor != null) {
				dto.setFornecedor(fornecedor);
			}
			
			dto.setDescription(form.getDescription());
			dto.setName(form.getName());
			dto.setPrice(form.getPrice());

			newProduct = new Product(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productRepository.save(newProduct);
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			verifyProduct(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		fornecedorRepository.deleteById(id);
	}
	
	public void verifyProduct(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		if (!obj.isPresent()) {
			throw new ProductException(FORNECEDOR_NOT_FOUND + id, HttpStatus.NOT_FOUND);
		}
	}
	
}
