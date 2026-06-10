package fr.visiplus.book.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
	
	@Test
	public void testReserveAvailableBook() throws Exception {

	    // Arrange
	    Long bookId = 3L; // ✅ AVAILABLE
	    Long userId = 1L;

	    String endpointUrl = "/book/reserve/" + bookId + "/" + userId;

	    // Act
	    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(endpointUrl));

	    // Assert
	    resultActions.andExpect(MockMvcResultMatchers.status().isOk());
	    resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.bookDTO.id").value(3));
	    resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.userDTO.id").value(1));
	    resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.bookDTO.status").value("BOOKED"));

	}
	
	@Test
	public void testReserveBorrowedBookShouldFail() throws Exception {

	    // Arrange
	    Long bookId = 4L;
	    Long userId = 1L;

	    String endpointUrl = "/book/reserve/" + bookId + "/" + userId;

	    // Act
	    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(endpointUrl));

	    // Assert
	    resultActions.andExpect(MockMvcResultMatchers.status().isForbidden());
	}
	
}
