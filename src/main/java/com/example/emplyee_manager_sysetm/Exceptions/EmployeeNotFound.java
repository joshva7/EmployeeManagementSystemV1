package com.example.emplyee_manager_sysetm.Exceptions;

public class EmployeeNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public EmployeeNotFound(String message) {
		super(message);
	}
}
