package com.wlailton.curriculumMatrixapi.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.wlailton.curriculumMatrixapi.AbstractMvcTest;
import com.wlailton.curriculumMatrixapi.model.Course;
import com.wlailton.curriculumMatrixapi.repositories.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest extends AbstractMvcTest {

	@Autowired
	private CourseRepository courseRepository;

	private String token;

	@Before
	public void init() throws Exception {
		this.token = getToken("usertest", "123456");
	}

	@Test
	public void contexLoads() throws Exception {
		assertThat(courseRepository).isNotNull();
	}

	@Test
	public void getCourseNotExistId() throws Exception {
		Long id = 999L;
		if (!courseRepository.findById(id).isPresent()) {
			mockMvc.perform(get("/api/course/" + 999L).header("Authorization", this.token)).andDo(print())
					.andExpect(status().is(404));
		} else {
			new Exception();
		}
	}

	@Test
	public void createtNewCourseAndGetAndUpdateAndDelete() throws Exception {

		Course course = new Course();
		course.setName("Course Test");

		String requestJson = castObjectToJson(course);

		MvcResult result = mockMvc
				.perform(post("/api/course/").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
						.header("Authorization", this.token))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
		course.setId(jsonObject.getLong("id"));

		mockMvc.perform(get("/api/course/" + course.getId()).header("Authorization", this.token)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

		course.setName("Course Update");
		requestJson = castObjectToJson(course);

		mockMvc.perform(put("/api/course/" + course.getId().toString()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(requestJson).header("Authorization", this.token)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(course.getName()));

		mockMvc.perform(delete("/api/course/" + course.getId()).header("Authorization", this.token)).andDo(print())
				.andExpect(status().isOk());
	}
}
