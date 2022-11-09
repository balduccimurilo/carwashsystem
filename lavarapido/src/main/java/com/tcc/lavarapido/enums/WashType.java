package com.tcc.lavarapido.enums;

public enum WashType {
	
	COMPLETE("Completa"),
	BASIC("Basica");
	
	private String washType;

	private WashType(String washType) {
		this.washType = washType;
	}

	public String getWashType() {
		return washType;
	}

	public void setWashType(String washType) {
		this.washType = washType;
	}
	
}
