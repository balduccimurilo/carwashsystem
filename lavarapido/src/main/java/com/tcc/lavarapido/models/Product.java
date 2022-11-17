package com.tcc.lavarapido.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	private String name;
	private String description;
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	@JsonIgnore
	private Fornecedor fornecedor;

	public Product(Long idProduct, String name, String description, Double price) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.description = description;
		this.price = price;
	}

}
