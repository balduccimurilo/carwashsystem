package com.tcc.lavarapido.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;


import com.tcc.lavarapido.utils.UserType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Class Manager Entity
@Getter
@Setter
@NoArgsConstructor
@Entity
//@Table(name = "user", schema = "carwash")
@Table(name = "TB_USER")
//public class User implements Serializable, UserDetails {
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user;

	@Column(name = "name", nullable = false)
	private String name;
	@Column(nullable = false, length = 255)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, unique = true)
	private String cpf;
	@Column(nullable = false, unique = true)
	private String cel;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type", columnDefinition = "ENUM('MANAGER', 'CLIENT')")
	private UserType userType;

	@Column(name = "profiles")
	@OneToMany(fetch = FetchType.EAGER)
	private List<Profile> profiles = new ArrayList<>();

	@Column(name = "dt_creation", nullable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime dtCreation;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(Long id_user, String name, String email, String password, String cpf, String cel, UserType userType,
			List<Profile> profiles, LocalDateTime dtCreation) {
		this.id_user = id_user;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.cel = cel;
		this.userType = userType;
		this.profiles = profiles;
		this.dtCreation = dtCreation;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.profiles;
//	}
//
//	@Override
//	public String getUsername() {
//		return this.email;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}

}
