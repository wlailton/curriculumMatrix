package com.wlailton.curriculumMatrixapi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wlailton.curriculumMatrixapi.exception.CourseNotFoundException;
import com.wlailton.curriculumMatrixapi.model.Course;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	public void whenFindByIdThenReturnCourse() {
		Course course = new Course();
		course.setName("Test");
	
		entityManager.persist(course);
		entityManager.flush();

		Course courseFound = courseRepository.findById(course.getId())
				.orElseThrow(() -> new CourseNotFoundException(course.getId().toString()));

		assertThat(courseFound.getName()).isEqualTo(course.getName());
		
	}
}
