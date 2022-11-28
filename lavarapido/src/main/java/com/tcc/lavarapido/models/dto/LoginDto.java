package com.tcc.lavarapido.models.dto;

import com.tcc.lavarapido.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {

	private String token;
	private String type;
	private Long user_ID;
	private Role role;

}
