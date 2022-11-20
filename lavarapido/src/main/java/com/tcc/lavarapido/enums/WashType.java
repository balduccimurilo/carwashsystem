package com.tcc.lavarapido.enums;

public enum WashType {
	
	COMPLETE("Completa"),
	BASIC("Basica"),
	PRETINHO("Pretinho"),
	INTERNA("Interna Aspirador"),
	COMPLETACERA("Completa com Cera"),
	POLIMENTO("Polimento");
	
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
