package br.com.aijalon.medical.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DoctorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveriaDevolverBadRequestCasoDadosEstejamIncorretos() throws Exception {
		URI uri = new URI("/doctor");
		String json = "{\"name\": \"aijalon\",\"birthDate\":\"19/12/2000\",\"specialties\":\"[6]}";
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri).content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	@Test
	public void deveriaDevolverOk() throws Exception {
		URI uri = new URI("/doctor");
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}
	
}
