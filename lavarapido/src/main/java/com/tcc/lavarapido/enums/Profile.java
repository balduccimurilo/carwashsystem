package com.tcc.lavarapido.enums;

public enum Profile {
	
	ADMIN("Administrador"),
	CLIENT("Cliente");
		
	private String profile;

	private Profile(String profile) {
		this.profile = profile;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	
}
