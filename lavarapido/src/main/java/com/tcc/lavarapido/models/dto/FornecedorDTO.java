package com.tcc.lavarapido.models.dto;

import com.tcc.lavarapido.models.Fornecedor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {

	private Long id;
	private String name;
	private String fantasyName;
	private String razaoSocial;
	private String address;
	private String cel;
	private String cnpj;
	private String email;

	public FornecedorDTO(Fornecedor fornecedor) {
		this.id = fornecedor.getIdFornecedor();
		this.address = fornecedor.getAddress();
		this.cel = fornecedor.getCel();
		this.cnpj = fornecedor.getCnpj();
		this.fantasyName = fornecedor.getFantasyName();
		this.name = fornecedor.getName();
		this.razaoSocial = fornecedor.getRazaoSocial();
		this.email = fornecedor.getEmail();
	}

}
