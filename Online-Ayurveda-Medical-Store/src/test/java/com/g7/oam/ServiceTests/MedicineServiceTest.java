package com.g7.oam.ServiceTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.PersistenceException;

import java.lang.ModuleLayer.Controller;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.config.RepositoryConfigurationUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.g7.oam.entities.Category;
import com.g7.oam.entities.Company;
import com.g7.oam.entities.Medicine;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.repository.IMedicineRepository;
import com.g7.oam.service.IMedicineService;
import com.g7.oam.service.MedicineServiceImpl;
import com.google.common.base.Optional;

import net.bytebuddy.description.modifier.EnumerationState;

@SpringBootTest
public class MedicineServiceTest {
	
	IMedicineRepository repository;
	private static MedicineServiceImpl service;
	private static AutoCloseable ac;
	
@BeforeEach
public void doinit() {
	repository=mock(IMedicineRepository.class);
	service = new MedicineServiceImpl(repository);
	ac = MockitoAnnotations.openMocks(this);
}
	
@AfterEach
public void doAtEnd() throws Exception
{
	ac.close();
}
	@Test
	@DisplayName("Test medicine add with medicine details")
	public void testAddMedicineDetails(){
		
		
		
		Medicine testInput = new Medicine(44,"crocinx",(float)10,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Reddy,new Category(42,"Digestion"));
		Medicine actual = new Medicine(44,"crocinx",(float)10,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Reddy,new Category(42,"Digestion"));
		
		
		
		
		when(repository.save(testInput)).thenReturn(actual);
		service.addMedicine(testInput);
		assertEquals(testInput,actual);
		verify(repository).save(testInput);
		
	}
	
	@Test
	@DisplayName("False value Test for medicine add ")
	public void testFalseValueAddMedicineDetails(){
		
		
		
		Medicine testInput = new Medicine(44,"crocinx",(float)10,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Reddy,new Category(42,"Digestion"));
		Medicine actual = new Medicine(40,"crocinx",(float)10,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Reddy,new Category(42,"Digestion"));
		
		
		
		
		when(repository.save(testInput)).thenReturn(actual);
		service.addMedicine(testInput);
		assertNotEquals(testInput,actual);
		verify(repository).save(testInput);
		
	}
	@Test
	@DisplayName("Test medicine update with new medicine details")
	public void testUpdateMedicineDetails() throws MedicineNotFoundException{
		
		
		
		
			
		Medicine testOutput = new Medicine(44,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		java.util.Optional<Medicine> optionalMedicine = java.util.Optional.of(testOutput);	
		Medicine testsOutput = new Medicine(44,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
	//	Medicine med= mock(Medicine.class);
		
		
		when(repository.findById(testOutput.getMedicineId())).thenReturn(optionalMedicine);
		when(repository.save(testOutput)).thenReturn(testOutput);
		Medicine actual = service.updateMedicine(testOutput);
		assertEquals(testsOutput,actual);
		verify(repository).save(testOutput);
		
		
		
	}
	
	@Test
	@DisplayName("False value Test for medicine update ")
	public void testFalseUpdateMedicineDetails() throws MedicineNotFoundException{
		
		
		
		
			
		Medicine testOutput = new Medicine(44,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		java.util.Optional<Medicine> optionalMedicine = java.util.Optional.of(testOutput);	
		Medicine testsOutput = new Medicine(50,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
	//	Medicine med= mock(Medicine.class);
		
		
		when(repository.findById(testOutput.getMedicineId())).thenReturn(optionalMedicine);
		when(repository.save(testOutput)).thenReturn(testOutput);
		Medicine actual = service.updateMedicine(testOutput);
		assertNotEquals(testsOutput,actual);
		verify(repository).save(testOutput);
		
		
		
	}

	
	
	@Test
	@DisplayName("Test medicine delete")
	public void testDeleteMedicineDetails() throws MedicineNotFoundException{
		
		Medicine testOutput = new Medicine(44,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		java.util.Optional<Medicine> optionalMedicine = java.util.Optional.of(testOutput);	
		Medicine testsOutput = new Medicine(44,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		repository.save(testOutput);
		repository.deleteById(testOutput.getMedicineId());
		when(repository.findById(testOutput.getMedicineId())).thenReturn(optionalMedicine);
		assertEquals(optionalMedicine.get(),testsOutput);
			
			
		
	}
	
	@Test
	@DisplayName("False Test medicine delete")
	public void testFalseDeleteMedicineDetails() throws MedicineNotFoundException{
		
		Medicine testOutput = new Medicine(44,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		java.util.Optional<Medicine> optionalMedicine = java.util.Optional.of(testOutput);	
		Medicine testsOutput = new Medicine(414,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		repository.save(testOutput);
		repository.deleteById(testOutput.getMedicineId());
		when(repository.findById(testOutput.getMedicineId())).thenReturn(optionalMedicine);
		assertNotEquals(optionalMedicine.get(),testsOutput);
			
			
		
	}
		
	
	@Test
	@DisplayName("Test medicine view all")
	public void testViewAllMedicineDetails() throws MedicineNotFoundException{
		
		List<Medicine> medList=mock(List.class);
		
		
		
		when(repository.findAll()).thenReturn(medList);
		List<Medicine> actualList=service.showAllMedicine();
		verify(repository).findAll();
		assertIterableEquals(medList, actualList);
		
			
		
		
	}
	@Test
	@DisplayName("Test medicine view by id")
	public void testViewByIdMedicineDetails() throws MedicineNotFoundException{
		
		
		
		Medicine testInput = new Medicine(44,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		Medicine actual = new Medicine(44,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		
		
		when(repository.findById(testInput.getMedicineId())).thenReturn(java.util.Optional.of(actual));
		service.viewMedicine(testInput);
		verify(repository).findById(testInput.getMedicineId());
		assertEquals(testInput,actual);
			
	}
	@Test
	@DisplayName("False value Test for medicine view by id")
	public void testFalseValueViewByIdMedicineDetails() throws MedicineNotFoundException{
		
		
		
		Medicine testInput = new Medicine(12,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		Medicine actual = new Medicine(44,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		
		
		when(repository.findById(testInput.getMedicineId())).thenReturn(java.util.Optional.of(actual));
		service.viewMedicine(testInput);
		verify(repository).findById(testInput.getMedicineId());
		assertNotEquals(testInput,actual);
			
	}
	
	
	
	
	
	
	
}
