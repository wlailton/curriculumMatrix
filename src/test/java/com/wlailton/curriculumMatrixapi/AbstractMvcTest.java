package com.wlailton.curriculumMatrixapi;


import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

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
}
