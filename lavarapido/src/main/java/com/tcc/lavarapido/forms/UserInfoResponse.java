package com.tcc.lavarapido.forms;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private List<String> profiles;

	public UserInfoResponse(Long id, String username, String email, List<String> profiles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.profiles = profiles;
	}
}
