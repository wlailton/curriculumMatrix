package com.wlailton.curriculumMatrixapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wlailton.curriculumMatrixapi.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
