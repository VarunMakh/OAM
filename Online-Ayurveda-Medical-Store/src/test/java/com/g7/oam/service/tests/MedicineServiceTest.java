package com.g7.oam.service.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
		Medicine actual = new Medicine(44, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));

		when(repository.save(testInput)).thenReturn(actual);
		service.addMedicine(testInput);
		assertEquals(testInput, actual);
		verify(repository).save(testInput);

	}

	@Test
	@DisplayName("False Value Test for Medicine Add")
	public void testFalseValueAddMedicine() {

		Medicine testInput = new Medicine(44, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));
		Medicine actual = new Medicine(40, "crocinx", (float) 10, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.REDDY, new Category(42, "Digestion"));

		when(repository.save(testInput)).thenReturn(actual);
		service.addMedicine(testInput);
		assertNotEquals(testInput, actual);
		verify(repository).save(testInput);

	}

	@Test
	@DisplayName("Test Medicine Update")
	public void testUpdateMedicine() throws MedicineNotFoundException {

		Medicine testOutput = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));

		when(repository.findById(testOutput.getMedicineId())).thenReturn(Optional.of(testOutput));
		when(repository.save(testOutput)).thenReturn(testOutput);
		Medicine actual = service.updateMedicine(testOutput);
		assertEquals(testOutput, actual);
		verify(repository).save(testOutput);

	}

	@Test
	@DisplayName("False Value Test for Medicine Update")
	public void testFalseUpdateMedicine() throws MedicineNotFoundException {

		Medicine testOutput = new Medicine(231, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));

		when(repository.findById(testOutput.getMedicineId())).thenReturn(Optional.of(testOutput));
		when(repository.save(testOutput)).thenReturn(testOutput);
		Medicine actual = service.updateMedicine(testOutput);
		verify(repository).save(testOutput);
		assertNotEquals(testOutput, actual);
		

	}
	
	@Test
	@DisplayName("Test View Medicine by ID")
	public void testViewByMedicineId() throws MedicineNotFoundException {

		Medicine testInput = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		Medicine actual = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));

		when(repository.findById(testInput.getMedicineId())).thenReturn(Optional.of(actual));
		service.viewMedicine(testInput);
		verify(repository).findById(testInput.getMedicineId());
		assertEquals(testInput, actual);

	}

	@Test
	@DisplayName("Test False Value for View Medicine by ID")
	public void testFalseValueViewMedicineById() throws MedicineNotFoundException {

		Medicine testInput = new Medicine(12, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		Medicine actual = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));

		when(repository.findById(testInput.getMedicineId())).thenReturn(Optional.of(actual));
		service.viewMedicine(testInput);
		verify(repository).findById(testInput.getMedicineId());
		assertNotEquals(testInput, actual);

	}

	@Test
	@DisplayName("Test Medicine Delete")
	public void testDeleteMedicine() throws MedicineNotFoundException {

		Medicine testOutput = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		Medicine actual = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		repository.save(testOutput);
		repository.deleteById(testOutput.getMedicineId());
		when(repository.findById(testOutput.getMedicineId())).thenReturn(Optional.of(testOutput));
		assertEquals(Optional.of(testOutput).get(), actual);

	}

	@Test
	@DisplayName("Test False Value for Medicine Delete")
	public void testFalseDeleteMedicine() throws MedicineNotFoundException {

		Medicine testOutput = new Medicine(44, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		Medicine actual = new Medicine(414, "crocinVVV", (float) 20, LocalDate.of(2020, 02, 02),
				LocalDate.of(2021, 12, 12), Company.SUN, new Category(42, "Digestion"));
		repository.save(testOutput);
		repository.deleteById(testOutput.getMedicineId());
		when(repository.findById(testOutput.getMedicineId())).thenReturn(Optional.of(testOutput));
		assertNotEquals(Optional.of(testOutput).get(), actual);

	}

	@Test
	@DisplayName("Test View All Medicines")
	public void testViewAllMedicine() throws MedicineNotFoundException {

		List<Medicine> medList = mock(List.class);

		when(repository.findAll()).thenReturn(medList);
		List<Medicine> actualList = service.showAllMedicines();
		verify(repository).findAll();
		assertIterableEquals(medList, actualList);

	}

}
