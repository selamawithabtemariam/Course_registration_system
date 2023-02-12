package edu.mum.se.mumsched.service.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	public NotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
