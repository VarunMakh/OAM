package com.g7.oam.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
	@DisplayName("Test medicine update with new medicine details")
	public void testUpdateMedicineDetails() throws MedicineNotFoundException{
		
		
		
		Medicine testInput = new Medicine(96,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		Medicine actual = new Medicine(96,"crocinVVV",(float)20,LocalDate.of(2020,02,02),LocalDate.of(2021,12,12), Company.Sun,new Category(42,"Digestion"));
		
		
		
		when(repository.save(testInput)).thenReturn(actual);
		service.updateMedicine(testInput);
		assertEquals(testInput,actual);
		verify(repository).save(testInput);
		
	}
	
	
	
	
	
}
