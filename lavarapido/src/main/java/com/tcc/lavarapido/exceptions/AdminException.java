package com.tcc.lavarapido.exceptions;

import org.springframework.http.HttpStatus;

public class AdminException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

}
