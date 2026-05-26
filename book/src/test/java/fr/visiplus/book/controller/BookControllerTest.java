package fr.visiplus.book.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {
	
	@Autowired
	protected MockMvc mockMvc;
	

	@Test
	public void testGetAllBook() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/book"));
		
		resultActions = resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
		resultActions.andExpect(jsonPath("$",hasSize(4)));	
	}


}
