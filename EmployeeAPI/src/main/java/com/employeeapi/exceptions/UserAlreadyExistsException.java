package com.employeeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

	private static final String message =  "User already exists !!!";
	public UserAlreadyExistsException() {
		super(message);
	}

	 
}
