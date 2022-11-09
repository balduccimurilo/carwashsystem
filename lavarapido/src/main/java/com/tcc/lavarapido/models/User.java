package com.tcc.lavarapido.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.tcc.lavarapido.enums.Profile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id_user;

	protected String name;
	protected String email;
	protected String password;
	protected String cpf;
	protected String cel;

	protected Profile profile;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(Long id_user, String name, String email, String password, String cpf, String cel, Profile profile) {
		this.id_user = id_user;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.cel = cel;
		this.profile = profile;
	}

	public User(Long id_user, String name, String email, String password, String cpf, String cel) {
		this.id_user = id_user;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.cel = cel;
	}

	public void addProfile(Profile profile) {
		Profile.values();
	}

}
