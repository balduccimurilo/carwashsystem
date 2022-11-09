package com.tcc.lavarapido.models.dto;

import java.io.Serializable;

import com.tcc.lavarapido.enums.Profile;
import com.tcc.lavarapido.models.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Long id;
	
	protected String name;
	
	protected String email;
	
	private String password;
	
	protected String cpf;

	private String cel;
	
	protected Profile profile;

	public ClientDTO (Client client) {
		this.id = client.getId_user();
		this.name = client.getName();
		this.password = client.getPassword();
		this.email = client.getEmail();
		this.cpf = client.getCpf();
		this.cel = client.getCel();
		this.profile = profile.CLIENT;
	}
	
}
