package com.wlailton.curriculumMatrixapi.exception;

public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CourseNotFoundException(String id){
		super("Could not find a course with the id: " + id);
	}

}
