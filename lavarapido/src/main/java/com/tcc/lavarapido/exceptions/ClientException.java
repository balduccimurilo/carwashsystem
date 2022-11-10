package com.tcc.lavarapido.exceptions;

import org.springframework.http.HttpStatus;

public class ClientException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

}
