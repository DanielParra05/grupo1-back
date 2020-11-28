package com.ceiba.library.exception;

public class LoadException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public LoadException(String message) {
		super(message);
	}
}
