package com.SpringBootProject.exception;

public class NotFoundException extends RuntimeException{

	private String message;
	
	public NotFoundException() {
		
	}

	public NotFoundException(String message) {
		super(message);
		this.message = message;
	}

}
