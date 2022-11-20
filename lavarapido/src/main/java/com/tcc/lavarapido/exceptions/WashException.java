package com.tcc.lavarapido.exceptions;

import org.springframework.http.HttpStatus;

public class WashException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WashException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
		// TODO Auto-generated constructor stub
	}

}
