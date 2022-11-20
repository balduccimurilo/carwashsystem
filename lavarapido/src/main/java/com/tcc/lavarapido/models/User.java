package com.tcc.lavarapido.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcc.lavarapido.enums.IProfile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_tb")
@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user;
	
	@NotBlank
	private String username;
	
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@JsonIgnore
	private String password;
	
	private String cpf;
	private String cel;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Wash> washes = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_profiles", 
    	joinColumns = @JoinColumn(name = "user_id"),
    	inverseJoinColumns = @JoinColumn(name = "profile_id"))
	private Set<Profile> profiles = new HashSet<>();

	public User(String username, String email, String password) {
	    this.username = username;
	    this.email = email;
	    this.password = password;
	  }
	
	public User(String name, String username, String email, String password, String cpf, String cel) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.cel = cel;
	}

	public User(Long id_user, String name, String username, String email, String password, String cpf, String cel, Set<Profile> profile) {
		this.id_user = id_user;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.cel = cel;
		this.profiles = profile;
	}

	public User(Long id_user, String name, String email, String password, String cpf, String cel) {
		this.id_user = id_user;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.cel = cel;
	}

	public void addProfile(IProfile profile) {
		IProfile.values();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cel == null) ? 0 : cel.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((profiles == null) ? 0 : profiles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((washes == null) ? 0 : washes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (cel == null) {
			if (other.cel != null)
				return false;
		} else if (!cel.equals(other.cel))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id_user == null) {
			if (other.id_user != null)
				return false;
		} else if (!id_user.equals(other.id_user))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (profiles == null) {
			if (other.profiles != null)
				return false;
		} else if (!profiles.equals(other.profiles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (washes == null) {
			if (other.washes != null)
				return false;
		} else if (!washes.equals(other.washes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", username=" + username + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", cpf=" + cpf + ", cel=" + cel + ", washes=" + washes + ", profiles="
				+ profiles + "]";
	}

	public User(Optional<User> obj) {
		this.id_user = obj.get().getId_user();
		this.name = obj.get().getName();
		this.username = obj.get().getUsername();
		this.password = obj.get().getPassword();
		this.cpf = obj.get().getCpf();
		this.cel = obj.get().getCel();
		this.email = obj.get().getEmail();
		this.profiles = obj.get().getProfiles();
		this.washes = obj.get().getWashes();
	}
	
	

}
