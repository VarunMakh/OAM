package com.g7.oam.service.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.g7.oam.entities.Company;
import com.g7.oam.entities.Customer;
import com.g7.oam.entities.Medicine;
import com.g7.oam.entities.Order;
import com.g7.oam.entities.OrderStatus;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.exception.OrderNotFoundException;
import com.g7.oam.repository.IOrderRepository;
import com.g7.oam.service.OrderServiceImpl;

@SpringBootTest
public class OrderServiceTest {
	IOrderRepository repository;
	private static OrderServiceImpl service;
	private static AutoCloseable ac;

	@BeforeEach
	public void doInit() {
		repository = mock(IOrderRepository.class);
		service = new OrderServiceImpl(repository);
		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}

	@Test
	@DisplayName("Test Order Add")
	public void testAddOrder() {

		List<Medicine> medicineList = new ArrayList<>();
		Medicine medicine1 = new Medicine(800, "crocin", 50, LocalDate.of(2024, 06, 04), LocalDate.of(2024, 02, 04),
				Company.SUN, new Category(900, "GC"));
		Medicine medicine2 = new Medicine(801, "crocin", 50, LocalDate.of(2024, 06, 04), LocalDate.of(2024, 02, 04),
				Company.SUN, new Category(900, "GC"));
		medicineList.add(medicine1);
		medicineList.add(medicine2);
		Order testInput = new Order(1, LocalDate.of(2020, 06, 05), medicineList, LocalDate.of(2024, 06, 04),
				(float) 1040, new Customer(12, "fad", "rahul"), OrderStatus.PLACED);
		Order expected = new Order(1, LocalDate.of(2020, 06, 05), medicineList, LocalDate.of(2024, 06, 04),
				(float) 1040, new Customer(12, "fad", "rahul"), OrderStatus.PLACED);

		when(repository.save(testInput)).thenReturn(expected);
		Order actual = service.addOrder(testInput);
		assertNotNull(actual);
		verify(repository).save(testInput);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("False Value Test for Order Add")
	public void testFalseValueAddOrder() {
		List<Medicine> obj = new ArrayList<>();
		obj.add(null);
		Order testInput = new Order(1, LocalDate.of(2020, 06, 05), obj, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);
		Order expected = null;

		when(repository.save(testInput)).thenReturn(expected);
		Order actual = service.addOrder(testInput);
		verify(repository).save(testInput);
		assertNull(actual);
	}

	@Test
	@DisplayName("Test Order Update")
	public void testUpdateOrder() throws OrderNotFoundException {

		Order testInput = new Order(1, LocalDate.of(2020, 06, 05), null, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);
		Order expected = new Order(1, LocalDate.of(2020, 06, 05), null, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);

		when(repository.findById(testInput.getOrderId())).thenReturn(Optional.of(expected));
		Order actual = service.updateOrder(expected);
		assertNotNull(actual);
		verify(repository).save(testInput);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("False Value Test for Order Update")
	public void testFalseValueUpdateOrder() throws OrderNotFoundException {

		List<Medicine> obj = new ArrayList<>();
		obj.add(null);
		Order testInput = new Order(1, LocalDate.of(2020, 06, 05), obj, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);
		Order expected = new Order(2, LocalDate.of(2020, 06, 05), obj, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);

		when(repository.findById(testInput.getOrderId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.updateOrder(expected);
		assertThrows(OrderNotFoundException.class, executable);
	}

	@Test
	@DisplayName("Test View Order by ID")
	public void testViewOrderById() throws OrderNotFoundException {

		Order testInput = new Order(1, LocalDate.of(2020, 06, 05), null, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);
		Order expected = new Order(1, LocalDate.of(2020, 06, 05), null, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);

		when(repository.findById(testInput.getOrderId())).thenReturn(Optional.of(expected));
		Order actual = service.viewOrder(testInput);
		assertNotNull(actual);
		verify(repository).findById(testInput.getOrderId());
		assertEquals(actual, expected);

	}

	@Test
	@DisplayName("False Value Test for View Order by ID")
	public void testFalseViewViewOrderById() throws OrderNotFoundException {

		Order testInput = new Order(1, LocalDate.of(2020, 06, 05), null, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);
		Order expected = new Order(2, LocalDate.of(2020, 06, 05), null, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);

		when(repository.findById(testInput.getOrderId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.viewOrder(expected);
		assertThrows(OrderNotFoundException.class, executable);

	}

	@Test
	@DisplayName("Test for Order Cancel")
	public void testCancelOrder() throws OrderNotFoundException {

		Order testInput = new Order(1, LocalDate.of(2020, 06, 05), null, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);
		Order expected = new Order(1, LocalDate.of(2020, 06, 05), null, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);

		when(repository.findById(testInput.getOrderId())).thenReturn(Optional.of(expected));
		Order actual = service.cancelOrder(testInput.getOrderId());
		assertNotNull(actual);
		verify(repository).deleteById(testInput.getOrderId());
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("False value Test for Order Cancel")
	public void testFalseViewCancelOrder() throws OrderNotFoundException {

		Order testInput = new Order(1, LocalDate.of(2020, 06, 05), null, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);
		Order expected = new Order(2, LocalDate.of(2020, 06, 05), null, LocalDate.of(2024, 06, 04), (float) 1040,
				new Customer(12, "fad", "rahul"), OrderStatus.PLACED);

		when(repository.findById(testInput.getOrderId())).thenReturn(Optional.of(expected));
		Executable executable = () -> service.cancelOrder(expected.getOrderId());
		assertThrows(OrderNotFoundException.class, executable);

	}

	@Test
	@DisplayName("Test View All Orders by medicineId")
	public void testViewAllOrders() {

		int medicineId = 1;
		List<Order> expectedList = mock(List.class);

		when(repository.findAll()).thenReturn(expectedList);
		List<Order> actualList = null;
		try {
			actualList = service.showAllOrders(medicineId);
		} catch (MedicineNotFoundException e) {
			e.printStackTrace();
		}
		assertNotNull(actualList);
		verify(repository).findAll();
		assertIterableEquals(expectedList, actualList);

	}

	@Test
	@DisplayName("Test View All Orders by customer")
	public void testViewAllOrders(Customer customer) throws CustomerNotFoundException {

		List<Order> expectedList = mock(List.class);

		when(repository.findAll()).thenReturn(expectedList);
		List<Order> actualList = service.showAllOrders(customer);
		assertNotNull(actualList);
		verify(repository).findAll();
		assertIterableEquals(expectedList, actualList);

	}

	@Test
	@DisplayName("Test View All Orders by date")
	public void testViewAllOrders(LocalDate date) {

		List<Order> expectedList = mock(List.class);

		when(repository.findAll()).thenReturn(expectedList);
		List<Order> actualList = service.showAllOrders(date);
		assertNotNull(actualList);
		verify(repository).findAll();
		assertIterableEquals(expectedList, actualList);

	}

}
