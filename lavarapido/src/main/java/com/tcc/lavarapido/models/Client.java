package com.tcc.lavarapido.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.tcc.lavarapido.enums.Profile;
import com.tcc.lavarapido.models.dto.ClientDTO;

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
	
	@OneToMany(mappedBy = "client")
	private List<Wash> washes = new ArrayList<>();
	
	public List<Wash> getWash() {
		return washes;
	}
	
	public void setWash(List<Wash> washes) {
        this.washes = washes;
    }
	
	public Client() {
		addProfile(Profile.CLIENT);
	}

	public Client(Long id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password, password);
		addProfile(Profile.CLIENT);
	}

	public Client(ClientDTO clientDto) {
		this.id_user = clientDto.getId();
		this.name = clientDto.getName();
		this.email = clientDto.getEmail();
		this.cel = clientDto.getCel();
		this.password = clientDto.getPassword();
		this.profile = profile.CLIENT;
	}
	
	
}
