package com.tcc.lavarapido.models.dto;


import com.tcc.lavarapido.models.Fornecedor;
import com.tcc.lavarapido.models.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
	
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Fornecedor fornecedor;

	
	public ProductDTO(Long id, String name, String description, Double price, Fornecedor fornecedor) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.fornecedor = fornecedor;
	}


	public ProductDTO(Product product) {
		this.id = product.getIdProduct();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.fornecedor = product.getFornecedor();
	}
	
	
	
}
