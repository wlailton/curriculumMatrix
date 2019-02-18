package com.wlailton.curriculumMatrixapi.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.wlailton.curriculumMatrixapi.AbstractMvcTest;
import com.wlailton.curriculumMatrixapi.repositories.UserRepository;
import com.wlailton.curriculumMatrixapi.security.SignUpForm;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends AbstractMvcTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	private String token;

	@Before
	public void init() throws Exception {
		this.token = getToken("usertest", "123456");
	}

	@Test
	public void contexLoads() throws Exception {
		assertThat(userRepository).isNotNull();
	}

	@Test
	public void getUserNotExistId() throws Exception {
		Long id = 999L;
		if (!userRepository.findById(id).isPresent()) {
			mockMvc.perform(get("/api/user/" + 999L).header("Authorization", this.token)).andDo(print())
					.andExpect(status().is(404));
		} else {
			new Exception();
		}

	}

	@Test
	public void createtNewUserAndGetAndUpdateAndDelete() throws Exception {

		// Create new user.
		Set<String> roles = new HashSet<String>();
		roles.add("admin");

		SignUpForm user = new SignUpForm("User junit", "userjunit", "userjunit@xpto.com", roles,
				"123456");
		String requestJson = castObjectToJson(user);

		MvcResult result = mockMvc
				.perform(post("/api/user/").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
						.header("Authorization", this.token))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Get user.
		String username = getAttributeJson(result.getResponse().getContentAsString(), "username");

		result = mockMvc.perform(get("/api/user/" + username).content(requestJson).header("Authorization", this.token))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Update user.
		user.setName("User Update");
		requestJson = castObjectToJson(user);

		mockMvc.perform(put("/api/user/" + username).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
				.header("Authorization", this.token)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()));

		// Delete user.
		mockMvc.perform(delete("/api/user/" + username).header("Authorization", this.token)).andDo(print())
				.andExpect(status().isOk());
	}

}
