package com.example.emplyee_manager_sysetm.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloableExceptionHandlers {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> HandleNotValidException(MethodArgumentNotValidException ex) {
		Map<String, Object> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put("message", error.getField());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmployeeNotFound.class)
	public ResponseEntity<Map<String, Object>> HandleEmployeeNotFound(EmployeeNotFound err) {
		Map<String, Object> error = new HashMap<>();
		error.put("Message", err.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomeIoexception.class)
	public ResponseEntity<Map<String, Object>> HandleCustomerIoException(CustomeIoexception err) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("Message", err.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomeFilenotfoundException.class)
	public ResponseEntity<Map<String, Object>> HandleCustomeFileNotFound(CustomeFilenotfoundException err) {
		Map<String, Object> error = new HashMap<>();
		error.put("Message", err.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomeNullPointerExcepition.class)
	public ResponseEntity<Map<String, Object>> HandleCustomeNullPointerExcepiton(CustomeNullPointerExcepition err) {
		Map<String, Object> error = new HashMap<>();
		error.put("Message", err.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
