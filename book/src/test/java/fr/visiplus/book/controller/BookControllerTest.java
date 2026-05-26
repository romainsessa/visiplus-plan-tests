package fr.visiplus.book.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.visiplus.book.dto.BookDTO;

@SpringBootTest
public class BookControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	//protected MockMvc mockMvc;
	

	
	@Test
	public void testGetAllBook() throws Exception {
		/*ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/book"));
		resultActions = resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		resultActions.andExpect(jsonPath("$",hasSize(4)));*/
		
//		// Possibilité 1
		List<BookDTO> books = this.restTemplate.getForObject(
				"/book", 
				List.class);
//		assertEquals("romain",data.getUsername());		
//		
//		// Possibilité 2
//		ResponseEntity<Data> response = 
//				this.restTemplate.getForEntity("/api/romain", Data.class);
//		assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
//		assertEquals("romain", response.getBody().getUsername());	
	}


}
