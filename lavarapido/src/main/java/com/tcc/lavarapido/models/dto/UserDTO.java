package com.tcc.lavarapido.models.dto;

import java.util.Set;

import com.tcc.lavarapido.models.Profile;
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
	private Set<Profile> profile;
	
	public UserDTO (User user) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.cpf = cpf;
		this.cel = cel;
		this.profile = profile;
	}
}
