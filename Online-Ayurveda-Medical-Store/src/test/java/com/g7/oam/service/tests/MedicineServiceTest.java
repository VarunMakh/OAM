package com.g7.oam.service.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.g7.oam.entities.Category;
import com.g7.oam.entities.Company;
import com.g7.oam.entities.Medicine;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.repository.IMedicineRepository;
import com.g7.oam.service.MedicineServiceImpl;

@SpringBootTest
public class MedicineServiceTest {

	IMedicineRepository repository;
	private static MedicineServiceImpl service;
	private static AutoCloseable ac;

	@BeforeEach
	public void doinit() {
		repository = mock(IMedicineRepository.class);
		service = new MedicineServiceImpl(repository);
		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}

	@Test
	@DisplayName("Test Medicine Add")
	public void testAddMedicine() {

		Medicine testInput = new Medicine(44, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));
		Medicine expected = new Medicine(44, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));

		when(repository.save(testInput)).thenReturn(expected);
		Medicine actual = service.addMedicine(testInput);
		assertNotNull(actual);
		verify(repository).save(testInput);
		assertEquals(expected, actual);
		

	}

	@Test
	@DisplayName("False Value Test for Medicine Add")
	public void testFalseValueAddMedicine() {

		Medicine testInput = new Medicine(44, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));
		Medicine expected = new Medicine(40, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));

		when(repository.save(testInput)).thenReturn(expected);
		Medicine actual = service.addMedicine(testInput);
		verify(repository).save(testInput);
		assertNotNull(actual);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("Test Medicine Update")
	public void testUpdateMedicine() throws MedicineNotFoundException {

		Medicine testInput = new Medicine(44, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));
		Medicine expected = new Medicine(44, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));

		when(repository.findById(testInput.getMedicineId())).thenReturn(Optional.of(expected));
		Medicine actual = service.updateMedicine(testInput);
		assertNotNull(actual);
		verify(repository).save(testInput);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("False Value Test for Medicine Update")
	public void testFalseUpdateMedicine() throws MedicineNotFoundException {

		Medicine testInput = new Medicine(44, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));
		Medicine expected = new Medicine(412, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));

		when(repository.findById(testInput.getMedicineId())).thenReturn(Optional.of(expected));
		Executable executable = ()-> service.updateMedicine(expected);
		assertThrows(MedicineNotFoundException.class,executable);
		
	}
	
	@Test
	@DisplayName("Test View Medicine by ID")
	public void testViewByMedicineId() throws MedicineNotFoundException {

		Medicine testInput = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		Medicine expected = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));

		when(repository.findById(testInput.getMedicineId())).thenReturn(Optional.of(expected));
		Medicine actual = service.updateMedicine(testInput);
		assertNotNull(actual);
		verify(repository).findById(testInput.getMedicineId());
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("Test False Value for View Medicine by ID")
	public void testFalseValueViewMedicineById() throws MedicineNotFoundException {

		Medicine testInput = new Medicine(12, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		Medicine expected = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));

		when(repository.findById(testInput.getMedicineId())).thenReturn(Optional.of(expected));
		Executable executable =() -> service.updateMedicine(expected);
	    assertThrows(MedicineNotFoundException.class,executable);
	}

	@Test
	@DisplayName("Test Medicine Delete")
	public void testDeleteMedicine() throws MedicineNotFoundException {

		Medicine testInput = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		Medicine expected = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		
		when(repository.findById(testInput.getMedicineId())).thenReturn(Optional.of(expected));
		Medicine actual = service.deleteMedicine(testInput.getMedicineId());
		assertNotNull(actual);
		verify(repository).deleteById(testInput.getMedicineId());
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("Test False Value for Medicine Delete")
	public void testFalseDeleteMedicine() throws MedicineNotFoundException {

		Medicine testInput = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		Medicine expected = new Medicine(414, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		
		when(repository.findById(testInput.getMedicineId())).thenReturn(Optional.of(expected));
		Executable executable = ()-> service.updateMedicine(expected);
		assertThrows(MedicineNotFoundException.class,executable);
	}

	@Test
	@DisplayName("Test View All Medicines")
	public void testViewAllMedicine() throws MedicineNotFoundException {

		List<Medicine> medList = mock(List.class);

		when(repository.findAll()).thenReturn(medList);
		List<Medicine> actualList = service.showAllMedicines();
		assertNotNull(actualList);
		verify(repository).findAll();
		assertIterableEquals(medList, actualList);

	}

}
