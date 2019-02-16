package com.wlailton.curriculumMatrixapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wlailton.curriculumMatrixapi.exception.CourseNotFoundException;
import com.wlailton.curriculumMatrixapi.model.Course;
import com.wlailton.curriculumMatrixapi.repositories.CourseRepository;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	/**
	 * Get a course.
	 */
	@GetMapping("/{id}")
	public Course getCourse(@PathVariable String id) {
		return courseRepository.findById(Long.parseLong(id)).orElseThrow(() -> new CourseNotFoundException(id));

	}

	/**
	 * Delete a course.
	 */
	@DeleteMapping("/{id}")
	public void deleteCourse(@PathVariable String id) {
		courseRepository.findById(Long.parseLong(id)).orElseThrow(() -> new CourseNotFoundException(id));
		courseRepository.deleteById(Long.parseLong(id));

	}

	/**
	 * Create a new course.
	 */
	@PostMapping("/")
	public Course createCourse(@Valid @RequestBody Course course) {
		return courseRepository.save(course);
	}

	/**
	 * Update a course.
	 */
	@PutMapping("/{id}")
	public Course updateCourse(@PathVariable String id, @Valid @RequestBody Course courseUpdated) {
		courseRepository.findById(Long.parseLong(id)).orElseThrow(() -> new CourseNotFoundException(id));
		courseUpdated.setId(Long.parseLong(id));
		return courseRepository.save(courseUpdated);
	}

}
