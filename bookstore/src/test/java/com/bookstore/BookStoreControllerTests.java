package com.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.bookstore.controller.BookStoreController;
import com.bookstore.entity.Book;
import com.bookstore.services.BookStoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookStoreController.class)
public class BookStoreControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private BookStoreService bsService;
	
	/**
	@Test
	public void testListBooks() throws Exception{
		List<Book> listBooks = new ArrayList<Book>();
		listBooks.add(new Book(1, "Extensive Multithreading", "Santosh Kumar", "Mcgraw Publication", "Programming", 100, 50));
		Mockito.when(bsService.getBooks()).thenReturn(listBooks);
		
		String url = "http://localhost:8080/bookservice/books";
		
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println(actualJsonResponse);
		String expectedJsonResponse = objectMapper.writeValueAsString(listBooks);
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);			
	}
	**/
	
	
	@Test
	public void testCreateBook() throws JsonProcessingException, Exception{
		Book newBook = new Book("Tik toks", "Karla Reynoso", "Editorial Chalco", "Programming", 20, 200);
		
		//Book newBook = new Book(7, "Tik toks", "Karla Reynoso", "Editorial Chalco", "Programming", 20, 200);
		Book savedBook = new Book(7, "Tik toks", "Karla Reynoso", "Editorial Chalco", "Programming", 20, 200);
		//Mockito.when(bsService.createBook(newBook)).thenReturn(savedBook);
		Mockito.when(bsService.createBook(newBook)).thenReturn(newBook);
		
		String url = "http://localhost:8080/bookservice/books";
		//String url = "books";
		
		String result = objectMapper.writeValueAsString(bsService.createBook(newBook));
		
		mockMvc.perform(
				post(url)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(newBook))
				.with(csrf())
				).andExpect(status().isOk())
				//.andExpect(content().string("1"));
				.andExpect(content().string(result));
		
	}
	
	
}
