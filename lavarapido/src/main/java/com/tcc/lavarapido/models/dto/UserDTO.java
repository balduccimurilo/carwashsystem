package com.tcc.lavarapido.models.dto;

import com.tcc.lavarapido.enums.Profile;
import com.tcc.lavarapido.models.Admin;
import com.tcc.lavarapido.models.Client;
import com.tcc.lavarapido.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private String cpf;
	private String cel;
	private Profile profile;
	
	public UserDTO (User user) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.cpf = cpf;
		this.cel = cel;
		this.profile = profile;
	}
	
	public UserDTO (Client client) {
		this.id = client.getId_user();
		this.name = client.getName();
		this.password = client.getPassword();
		this.email = client.getEmail();
		this.cpf = client.getCpf();
		this.cel = client.getCel();
		this.profile = client.getProfile();
	}
	
	public UserDTO (Admin admin) {
		this.id = admin.getId_user();
		this.name = admin.getName();
		this.password = admin.getPassword();
		this.email = admin.getEmail();
		this.cpf = admin.getCpf();
		this.cel = admin.getCel();
		this.profile = admin.getProfile();
	}
}
