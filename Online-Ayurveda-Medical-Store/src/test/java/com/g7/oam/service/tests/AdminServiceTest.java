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

import com.g7.oam.entities.Admin;
import com.g7.oam.exception.AdminNotFoundException;
import com.g7.oam.repository.IAdminRepository;
import com.g7.oam.service.AdminServiceImpl;

@SpringBootTest
public class AdminServiceTest {

	IAdminRepository repository;
	private static AdminServiceImpl service;
	private static AutoCloseable ac;

	@BeforeEach
	public void doInit() {
		repository = mock(IAdminRepository.class);
		service = new AdminServiceImpl(repository);
		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}

	@Test
	@DisplayName("Test Admin Add")
	public void testAddAdmin() {

		Admin testInput = new Admin(1, "9876", "XYZ");
		Admin expected = new Admin(1, "9876", "XYZ");

		when(repository.save(testInput)).thenReturn(expected);
		Admin actual = service.addAdmin(testInput);
		assertNotNull(actual);
		verify(repository).save(testInput);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("False Value Test for Admin Add")
	public void testFalseValueAddAdmin() {

		Admin testInput = new Admin(2, "1234", "Al");
		Admin expected = null;

		when(repository.save(testInput)).thenReturn(expected);
		Admin actual = service.addAdmin(testInput);
		verify(repository).save(testInput);
		assertNull(actual);

	}

	@Test
	@DisplayName("Test Admin Update")
	public void testUpdateAdmin() throws AdminNotFoundException {

		Admin testInput = new Admin(1, "abcd", "XYZ");
		Admin expected = new Admin(1, "abcd", "XYZ");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Admin actual = service.updateAdmin(expected);
		assertNotNull(actual);
		verify(repository).save(testInput);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("False Value Test for Admin Update")
	public void testFalseValueUpdateAdmin() throws AdminNotFoundException {

		Admin testInput = new Admin(1, "abcd", "XYZ");
		Admin expected = new Admin(2, "abcd", "XYZ");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.updateAdmin(expected);
		assertThrows(AdminNotFoundException.class, executable);

	}

	@Test
	@DisplayName("Test View Admin by ID")
	public void testViewAdminById() throws AdminNotFoundException {

		Admin testInput = new Admin(3, "1234", "Mahesh");
		Admin expected = new Admin(3, "1234", "Mahesh");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Admin actual = service.viewAdmin(testInput);
		assertNotNull(actual);
		verify(repository).findById(testInput.getUserId());
		assertEquals(actual, expected);

	}

	@Test
	@DisplayName("Test False Value for View Admin by ID")
	public void testFalseValueViewAdminById() throws AdminNotFoundException {

		Admin testInput = new Admin(4, "1234", "Ramesh");
		Admin expected = new Admin(5, "1234", "Ramesh");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.viewAdmin(expected);
		assertThrows(AdminNotFoundException.class, executable);

	}

	@Test
	@DisplayName("Test Admin Delete")
	public void testDeleteAdmin() throws AdminNotFoundException {

		Admin testInput = new Admin(5, "1234", "Suresh");
		Admin expected = new Admin(5, "1234", "Suresh");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Admin actual = service.deleteAdmin(testInput.getUserId());
		assertNotNull(actual);
		verify(repository).deleteById(testInput.getUserId());
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("Test False Value for Admin Delete")
	public void testFalseValueDeleteAdmin() throws AdminNotFoundException {

		Admin testInput = new Admin(5, "1234", "Suresh");
		Admin expected = new Admin(6, "1234", "Suresh");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.deleteAdmin(expected.getUserId());
		assertThrows(AdminNotFoundException.class, executable);

	}

	@Test
	@DisplayName("Test View All Admins")
	public void testViewAllAdmins() throws AdminNotFoundException {

		@SuppressWarnings("unchecked")
		List<Admin> expectedList = mock(List.class);

		when(repository.findAll()).thenReturn(expectedList);
		List<Admin> actualList = service.showAllAdmins();
		assertNotNull(actualList);
		verify(repository).findAll();
		assertIterableEquals(expectedList, actualList);

	}

}