package com.wlailton.curriculumMatrixapi.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
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
import com.wlailton.curriculumMatrixapi.enums.UserTypeEnum;
import com.wlailton.curriculumMatrixapi.model.User;
import com.wlailton.curriculumMatrixapi.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends AbstractMvcTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void contexLoads() throws Exception {
		assertThat(userRepository).isNotNull();
	}
	
	@Test
	public void getUserNotExistId() throws Exception {

		mockMvc.perform(get("/api/user/999"))
				.andDo(print()).andExpect(status().is(404));
		
		User user = new User();
		user.setName("Test");
		user.setEmail("test@xpto.com");
		user.setPassword("test");
		user.setUserType(UserTypeEnum.STUDENT);
		
		String requestJson = castObjectToJson(user);
		
		mockMvc.perform(put("/api/user/999").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
		.andDo(print()).andExpect(status().is(404));
		
	}
	
	@Test
	public void createtNewUserAndGetAndUpdateAndDelete() throws Exception {

		User user = new User();
		user.setName("Test");
		user.setEmail("test@xpto.com");
		user.setPassword("test");
		user.setUserType(UserTypeEnum.STUDENT);

		String requestJson = castObjectToJson(user);

		MvcResult result = mockMvc.perform(post("/api/user/").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		
		JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
		user.setId(jsonObject.getLong("id"));

		mockMvc.perform(get("/api/user/" + user.getId().toString())).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

		user.setName("User Update");
		requestJson = castObjectToJson(user);

		mockMvc.perform(put("/api/user/" + user.getId().toString()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(requestJson)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()));

		mockMvc.perform(delete("/api/user/" + user.getId())).andDo(print()).andExpect(status().isOk());
	}

}
