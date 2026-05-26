package fr.visiplus.book.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetAllBook() throws Exception {
		
		//Arrange
		String endpointUrl = "/book";
		int expectedNumberBook = 4;
		
		//Act
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(endpointUrl));
		
		//Assert
		resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		resultActions.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(expectedNumberBook)));
		
	}

}
