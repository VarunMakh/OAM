package com.g7.oam.service.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.g7.oam.repository.IUserRepository;
import com.g7.oam.service.UserServiceImpl;
import com.g7.oam.entities.User;

@SpringBootTest
public class UserServiceTest {
	
	IUserRepository repository;
	private static UserServiceImpl service;
	private static AutoCloseable ac;
	
	@BeforeEach
	public void doInit() {
		repository = mock(IUserRepository.class);
		service = new UserServiceImpl(repository);
		ac = MockitoAnnotations.openMocks(this);
	}
	@AfterEach
	public void doAtEnd() throws Exception{
		ac.close();
	}
	
   @Test
   @DisplayName("Test for View all users")
   public void testViewAllUsers() {
	   List<User> expectedList = mock(List.class);
	   
	   when(repository.findAll()).thenReturn(expectedList);
	   List<User> actualList = service.showAllUsers();
	   assertNotNull(actualList);
	   verify(repository).findAll();
	   assertIterableEquals(expectedList, actualList);
	   
   }


}
