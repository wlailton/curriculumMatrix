package com.wlailton.curriculumMatrixapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wlailton.curriculumMatrixapi.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	
	@Query("SELECT c.name"
		   + "   , s.year "
		   + "   , s.semesterNumber "
		   + "   , d.name "
		   + "FROM Course c "
		   + "INNER JOIN c.semesters s "
		   + "INNER JOIN s.disciplines d "
		   + "WHERE c.id = :courseId "
		   + "AND s.year = :year")
	public Optional<List<Course>> findCurriculumMatrix(@Param("year") Long year, @Param("courseId") Long courseId);
	
	@Query("SELECT c.id "
			+ "  , c.name "
		  + " FROM Course c "
		  + "WHERE EXISTS (SELECT 1 "
		  + "                FROM Semester s "
		  + "               WHERE s.course.id = c.id "
		  + "                 AND s.year = :year "
		  + "                 AND s.disciplines IS NOT EMPTY)")
		public Optional<List<Course>> findCoursesHasMatrix(@Param("year") Long year);
}
