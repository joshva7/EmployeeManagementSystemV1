package com.example.emplyee_manager_sysetm.Exceptions;

public class CustomeFilenotfoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomeFilenotfoundException(String message) {
		super(message);
	}
}
