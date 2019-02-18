package com.wlailton.curriculumMatrixapi.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.wlailton.curriculumMatrixapi.AbstractMvcTest;
import com.wlailton.curriculumMatrixapi.security.SignUpForm;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest extends AbstractMvcTest {
	
	@Test
	public void signupUser() throws Exception {
		// Create new user.
		Set<String> roles = new HashSet<String>();
		roles.add("admin");

		SignUpForm user = new SignUpForm("User junit", "userjunit", "userjunit@xpto.com", roles, "123456");
		String requestJson = castObjectToJson(user);
		
		mockMvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	
	}
	

}
