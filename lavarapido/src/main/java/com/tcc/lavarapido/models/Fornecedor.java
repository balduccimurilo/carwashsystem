package com.tcc.lavarapido.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.tcc.lavarapido.models.dto.FornecedorDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Fornecedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFornecedor;

	private String name;
	private String fantasyName;
	private String razaoSocial;
	private String address;
	private String cel;
	private String cnpj;
	private String email;

	@OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
	private Set<Product> products = new HashSet<>();;

	public Fornecedor(Long idFornecedor, String name, String fantasyName, String razaoSocial, String address,
			String cel, String cnpj, Set<Product> products, String email) {
		super();
		this.idFornecedor = idFornecedor;
		this.name = name;
		this.fantasyName = fantasyName;
		this.razaoSocial = razaoSocial;
		this.address = address;
		this.cel = cel;
		this.cnpj = cnpj;
		this.email = email;
		this.products = products;
	}

	public Fornecedor(FornecedorDTO fornecedorDto) {
		this.idFornecedor = fornecedorDto.getId();
		this.name = fornecedorDto.getName();
		this.fantasyName = fornecedorDto.getFantasyName();
		this.razaoSocial = fornecedorDto.getRazaoSocial();
		this.address = fornecedorDto.getAddress();
		this.cel = fornecedorDto.getCel();
		this.cnpj = fornecedorDto.getCnpj();
		this.email = fornecedorDto.getEmail();
	}

	public Fornecedor(Long idFornecedor, String name, String fantasyName, String razaoSocial, String address,
			String cel, String cnpj, String email) {
		super();
		this.idFornecedor = idFornecedor;
		this.name = name;
		this.fantasyName = fantasyName;
		this.razaoSocial = razaoSocial;
		this.address = address;
		this.cel = cel;
		this.cnpj = cnpj;
		this.email = email;
	}

}
