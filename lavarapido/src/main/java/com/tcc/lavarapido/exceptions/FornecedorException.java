package com.tcc.lavarapido.exceptions;

import org.springframework.http.HttpStatus;

public class FornecedorException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FornecedorException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
		// TODO Auto-generated constructor stub
	}

}
