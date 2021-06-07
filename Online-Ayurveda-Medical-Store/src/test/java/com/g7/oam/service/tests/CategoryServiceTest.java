package com.g7.oam.service.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.g7.oam.entities.Category;
import com.g7.oam.exception.CategoryNotFoundException;
import com.g7.oam.repository.ICategoryRepository;
import com.g7.oam.service.CategoryServiceImpl;


@SpringBootTest
public class CategoryServiceTest {

	ICategoryRepository repository;
	private static CategoryServiceImpl service;
	private static AutoCloseable ac;

	@BeforeEach
	public void doInit() {
		repository = mock(ICategoryRepository.class);
		service = new CategoryServiceImpl(repository);
		ac = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}


	@Test
	@DisplayName("Test Category Add")
	public void testAddCustomer() {

		Category testInput = new Category(1, "ABC");
		Category expected = new Category(1, "ABC");

		when(repository.save(testInput)).thenReturn(expected);
		Category actual = service.addCategory(testInput);
		assertNotNull(actual);
		verify(repository).save(testInput);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("False Value Test for Category Add")
	public void testFalseValueAddCategory() {

		Category testInput = new Category(2, "AB");
		Category expected = null;

		when(repository.save(testInput)).thenReturn(expected);
		Category actual = service.addCategory(testInput);
		verify(repository).save(testInput);
		assertNull(actual);

	}
	
	@Test
	@DisplayName("Test Category Update")
	public void testUpdateCategory() throws CategoryNotFoundException {

		Category testInput = new Category(1, "abcd");
		Category expected = new Category(1, "abcd");

		when(repository.findById(testInput.getCategoryId())).thenReturn(Optional.of(expected));
		Category actual = service.updateCategory(expected);
		assertNotNull(actual);
		verify(repository).save(testInput);
		assertEquals(expected, actual);

	}


	@Test
	@DisplayName("False Value Test for Category Update")
	public void testFalseValueUpdateCategory() throws CategoryNotFoundException {

		Category testInput = new Category(1, "abcd");
		Category expected = new Category(2, "abcd");
		
		when(repository.findById(testInput.getCategoryId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.updateCategory(expected);
		assertThrows(CategoryNotFoundException.class, executable);

	}
	
	@Test
	@DisplayName("Test View Category by ID")
	public void testViewCategoryById() throws CategoryNotFoundException {

		Category testInput = new Category(3, "TB");
		Category expected = new Category(3, "TB");

		when(repository.findById(testInput.getCategoryId())).thenReturn(Optional.of(expected));
		Category actual = service.viewCategory(testInput);
		assertNotNull(actual);
		verify(repository).findById(testInput.getCategoryId());
		assertEquals(actual, expected);

	}

	@Test
	@DisplayName("Test False Value for View Catgeory by ID")
	public void testFalseValueViewCategoryById() throws CategoryNotFoundException {

		Category testInput = new Category(4, "TB");
		Category expected = new Category(5, "TB");

		when(repository.findById(testInput.getCategoryId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.viewCategory(expected);
		assertThrows(CategoryNotFoundException.class, executable);

	}
	
	@Test
	@DisplayName("Test Category Delete")
	public void testDeleteCategory() throws CategoryNotFoundException {

		Category testInput = new Category(5, "PQR");
		Category expected = new Category(5, "PQR");

		when(repository.findById(testInput.getCategoryId())).thenReturn(Optional.of(expected));
		Category actual = service.deleteCategory(testInput.getCategoryId());
		assertNotNull(actual);
		verify(repository).deleteById(testInput.getCategoryId());
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("Test False Value for Category Delete")
	public void testFalseValueDeleteCategory() throws CategoryNotFoundException {

		Category testInput = new Category(5, "PQR");
		Category expected = new Category(6, "PQR");

		when(repository.findById(testInput.getCategoryId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.deleteCategory(expected.getCategoryId());
		assertThrows(CategoryNotFoundException.class, executable);
		
	}
	@Test
	@DisplayName("Test View All Categories")
	public void testViewAllCategory() throws CategoryNotFoundException {
		
		@SuppressWarnings("unchecked")
		List<Category> expectedList = mock(List.class);

		when(repository.findAll()).thenReturn(expectedList);
		List<Category> actualList = service.showAllCategories();
		assertNotNull(actualList);
		verify(repository).findAll();
		assertIterableEquals(expectedList, actualList);

	}


}