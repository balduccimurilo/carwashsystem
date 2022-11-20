package com.tcc.lavarapido.forms;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	private Set<String> profiles;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	
	private String cpf;
	private String cel;
	private String name;
}