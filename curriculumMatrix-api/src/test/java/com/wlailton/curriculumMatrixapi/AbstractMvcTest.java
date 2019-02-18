package com.wlailton.curriculumMatrixapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wlailton.curriculumMatrixapi.security.LoginForm;

@SpringBootTest
@RunWith(SpringRunner.class)
@Ignore
public class AbstractMvcTest {

	@Autowired
	protected MockMvc mockMvc;

	protected String castObjectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(object);

	}
	
	protected String getAttributeJson(String jsonString, String attribut) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);
		return jsonObject.getString(attribut);
	}

	protected String getToken(String username, String password) throws Exception {
		final LoginForm auth = new LoginForm();
		auth.setUsername(username);
		auth.setPassword(password);
		MvcResult result = mockMvc.perform(
				post("/api/auth/signin").content(castObjectToJson(auth)).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
		return jsonObject.get("tokenType") + " " + jsonObject.get("accessToken");

	}

}
