package com.wlailton.curriculumMatrixapi.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String id) {
		super("Could not find a user with the username: " + id);
	}

}
