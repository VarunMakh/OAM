package com.g7.oam.service.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
		assertEquals(expected, actual);
		verify(repository).save(testInput);

	}

	@Test
	@DisplayName("False Value Test for Customer Add")
	public void testFalseValueAddCustomer() {

		Customer testInput = new Customer(2, "1234", "Al");
		Customer expected = null;

		when(repository.save(testInput)).thenReturn(expected);
		Customer actual = service.addCustomer(testInput);
		assertNull(actual);
		verify(repository).save(testInput);

	}

	@Test
	@DisplayName("Test Customemr Update")
	public void testUpdateCustomer() throws CustomerNotFoundException {

		Customer testOutput = new Customer(1, "abcd", "XYZ");
		Optional<Customer> optional = Optional.of(testOutput);

		when(repository.findById(testOutput.getUserId())).thenReturn(optional);
		when(repository.save(testOutput)).thenReturn(testOutput);
		Customer actual = service.updateCustomer(testOutput);
		assertEquals(testOutput, actual);
		verify(repository).save(testOutput);

	}

	@Test
	@DisplayName("False Value Test for Customer Update")
	public void testFalseValueUpdateCustomer() throws CustomerNotFoundException {

		Customer testOutput = new Customer(1, "abc", "XYZ");

		when(repository.findById(testOutput.getUserId())).thenReturn(Optional.of(testOutput));
		when(repository.save(testOutput)).thenReturn(testOutput);
		Customer actual = service.updateCustomer(testOutput);
		assertEquals(testOutput, actual);
		verify(repository).save(testOutput);

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
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("Test False Value for View Customer by ID")
	public void testFalseValueViewCustomerById() throws CustomerNotFoundException {

		Customer testInput = new Customer(4, "1234", "Vishnu");
		Customer expected = new Customer(4, "abcd", "Vishnu");

		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		Customer actual = service.viewCustomer(testInput);
		assertNotNull(actual);
		verify(repository).findById(testInput.getUserId());
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("Test Customer Delete")
	public void testDeleteCustomer() throws CustomerNotFoundException {

		Customer testInput = new Customer(5, "1234", "Shreyas");
		Customer expected = new Customer(5, "1234", "Shreyas");

		repository.save(testInput);
		repository.deleteById(testInput.getUserId());
		when(repository.findById(testInput.getUserId())).thenReturn(Optional.of(expected));
		assertEquals(testInput, expected);

	}

	@Test
	@DisplayName("Test False Value for Customer Delete")
	public void testFalseValueDeleteCustomer() throws CustomerNotFoundException {

		Customer testOutput = new Customer(6, "1234", "Shruthi");
		Customer actual = new Customer(5, "1234", "Shruthi");

		repository.save(testOutput);
		repository.deleteById(testOutput.getUserId());
		when(repository.findById(testOutput.getUserId())).thenReturn(Optional.of(testOutput));
		assertEquals(Optional.of(testOutput).get(), actual);

	}

	@Test
	@DisplayName("Test View All Customers")
	public void testViewAllCustomers() throws CustomerNotFoundException {

		List<Customer> expectedList = mock(List.class);

		when(repository.findAll()).thenReturn(expectedList);
		List<Customer> actualList = service.showAllCustomers();
		verify(repository).findAll();
		assertNotNull(expectedList);
		assertIterableEquals(expectedList, actualList);

	}

}
