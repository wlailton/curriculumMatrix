package com.wlailton.curriculumMatrixapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@PreAuthorize("hasRole('STUDENT')")
@CrossOrigin(origins = "*", maxAge = 3600)
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
	
	/**
	 * Get a curriculum matrix.
	 */
	@GetMapping("/matrix/{year}/{courseId}")
	public List<Course> getCurriculumMatrix(@PathVariable("year") String year, @PathVariable("courseId") String courseId) {
		return courseRepository.findCurriculumMatrix(Long.parseLong(year), Long.parseLong(courseId)).orElseThrow(() -> new CourseNotFoundException(courseId));

	}
	
	/**
	 * Get list courses has curriculum matrix.
	 */
	@GetMapping("/matrix/has/{year}")
	public List<Course> getCoursesHasCurriculumMatrix(@PathVariable("year") String year) {
		return courseRepository.findCoursesHasMatrix(Long.parseLong(year)).orElseThrow(() -> new CourseNotFoundException(year));

	}

}
