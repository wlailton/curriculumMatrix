package com.wlailton.curriculumMatrixapi.advise.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.wlailton.curriculumMatrixapi.exception.CourseNotFoundException;

@ControllerAdvice
public class CourseNotFoundExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(CourseNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String courseNotFoundHandler(CourseNotFoundException ex) {
		return ex.getMessage();
	}

}
