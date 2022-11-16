package com.tcc.lavarapido.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.tcc.lavarapido.enums.Profile;
import com.tcc.lavarapido.models.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "client", cascade=CascadeType.ALL)
	private Set<Wash> washes = new HashSet<>();

	
	public Client() {
		addProfile(Profile.CLIENT);
	}

	public Client(Long id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password, password);
		addProfile(Profile.CLIENT);
	}

	public Client(UserDTO clientDto) {
		this.id_user = clientDto.getId();
		this.name = clientDto.getName();
		this.email = clientDto.getEmail();
		this.cel = clientDto.getCel();
		this.cpf = clientDto.getCpf();
		this.password = clientDto.getPassword();
		this.profile = profile.CLIENT;
	}
	
	
}
