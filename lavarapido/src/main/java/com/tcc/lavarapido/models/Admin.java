package com.tcc.lavarapido.models;

import java.util.List;

import javax.persistence.Entity;

import com.tcc.lavarapido.enums.Profile;
import com.tcc.lavarapido.models.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Admin () {
		
	}

	public Admin(Long id_user, String name, String email, String password, String cpf, String cel, Profile profile) {
		super(id_user, name, email, password, cpf, cel, profile);
	}

	public Admin(Long id_user, String name, String email, String password, String cpf, String cel) {
		super(id_user, name, email, password, cpf, cel);
		addProfile(profile.ADMIN);
	}

	public Admin(String email, String password) {
		super(email, password);
	}
	
	public Admin(UserDTO adminDto) {
		this.id_user = adminDto.getId();
		this.name = adminDto.getName();
		this.email = adminDto.getEmail();
		this.cel = adminDto.getCel();
		this.cpf = adminDto.getCpf();
		this.password = adminDto.getPassword();
		this.profile = profile.ADMIN;
	}
}
