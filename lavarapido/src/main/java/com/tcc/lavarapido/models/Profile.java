package com.tcc.lavarapido.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//public class Profile implements GrantedAuthority {
	public class Profile  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

//	@Override
//	public String getAuthority() {
//		return name;
//	}
}
