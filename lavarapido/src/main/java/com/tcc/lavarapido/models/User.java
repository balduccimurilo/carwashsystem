package com.tcc.lavarapido.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcc.lavarapido.enums.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User implements UserDetails{

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
	
	@Column(columnDefinition = "ENUM('ADMIN', 'CLIENT')")
    @Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "profiles")
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Profile> profiles = new ArrayList<>();

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

	public User(Long id_user, String name, String username, String email, String password, String cpf, String cel, Role role) {
		this.id_user = id_user;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.cel = cel;
		this.role = role;
	}

	public void addProfile(Role profile) {
		Role.values();
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
		this.role = obj.get().getRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
