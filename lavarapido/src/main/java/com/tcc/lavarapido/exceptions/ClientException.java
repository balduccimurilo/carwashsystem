package com.tcc.lavarapido.exceptions;

import org.springframework.http.HttpStatus;

public class ClientException extends BaseException {

	public ClientException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

}
