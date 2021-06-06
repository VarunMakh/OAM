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

import com.g7.oam.entities.Customer;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.repository.ICustomerRepository;
import com.g7.oam.service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceTest {

	ICustomerRepository repository;
	private static CustomerServiceImpl service;
	private static AutoCloseable ac;

	@BeforeEach
	public void doInit() {
		repository = mock(ICustomerRepository.class);
		service = new CustomerServiceImpl(repository);
		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}

	@Test
	@DisplayName("Test Customer Add")
	public void testAddCustomer() {

		Customer testInput = new Customer(1, "1234", "ABC");
		Customer expected = new Customer(1, "1234", "ABC");

		when(repository.save(testInput)).thenReturn(expected);
		Customer actual = service.addCustomer(testInput);
		assertNotNull(actual);
		verify(repository).save(testInput);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("False Value Test for Customer Add")
	public void testFalseValueAddCustomer() {

		Customer testInput = new Customer(2, "1234", "Al");
		Customer expected = null;

		when(repository.save(testInput)).thenReturn(expected);
		Customer actual = service.addCustomer(testInput);
		verify(repository).save(testInput);
		assertNull(actual);

	}

	@Test
	@DisplayName("Test Customer Update")
	public void testUpdateCustomer() throws CustomerNotFoundException {

		Customer testInput = new Customer(1, "abcd", "XYZ");
		Customer expected = new Customer(1, "abcd", "XYZ");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Customer actual = service.updateCustomer(expected);
		assertNotNull(actual);
		verify(repository).save(testInput);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("False Value Test for Customer Update")
	public void testFalseValueUpdateCustomer() throws CustomerNotFoundException {

		Customer testInput = new Customer(1, "abcd", "XYZ");
		Customer expected = new Customer(2, "abcd", "XYZ");
		
		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.updateCustomer(expected);
		assertThrows(CustomerNotFoundException.class, executable);

	}

	@Test
	@DisplayName("Test View Customer by ID")
	public void testViewCustomerById() throws CustomerNotFoundException {

		Customer testInput = new Customer(3, "1234", "Vishnu");
		Customer expected = new Customer(3, "1234", "Vishnu");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Customer actual = service.viewCustomer(testInput);
		assertNotNull(actual);
		verify(repository).findById(testInput.getUserId());
		assertEquals(actual, expected);

	}

	@Test
	@DisplayName("Test False Value for View Customer by ID")
	public void testFalseValueViewCustomerById() throws CustomerNotFoundException {

		Customer testInput = new Customer(4, "1234", "Vishnu");
		Customer expected = new Customer(5, "1234", "Vishnu");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.viewCustomer(expected);
		assertThrows(CustomerNotFoundException.class, executable);

	}

	@Test
	@DisplayName("Test Customer Delete")
	public void testDeleteCustomer() throws CustomerNotFoundException {

		Customer testInput = new Customer(5, "1234", "Shreyas");
		Customer expected = new Customer(5, "1234", "Shreyas");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Customer actual = service.deleteCustomer(testInput.getUserId());
		assertNotNull(actual);
		verify(repository).deleteById(testInput.getUserId());
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("Test False Value for Customer Delete")
	public void testFalseValueDeleteCustomer() throws CustomerNotFoundException {

		Customer testInput = new Customer(5, "1234", "Shreyas");
		Customer expected = new Customer(6, "1234", "Shreyas");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.deleteCustomer(expected.getUserId());
		assertThrows(CustomerNotFoundException.class, executable);
		
	}
	@Test
	@DisplayName("Test View All Customers")
	public void testViewAllCustomers() throws CustomerNotFoundException {
		
		List<Customer> expectedList = mock(List.class);

		when(repository.findAll()).thenReturn(expectedList);
		List<Customer> actualList = service.showAllCustomers();
		assertNotNull(actualList);
		verify(repository).findAll();
		assertIterableEquals(expectedList, actualList);

	}

}
