package com.g7.oam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.g7.oam.entities.Medicine;

@Repository
public interface IMedicineRepository extends JpaRepository<Medicine, Integer> {
	
	@Query("SELECT m FROM Medicine m ORDER BY m.medicineCost DESC")
	List<Medicine> sortMedicinesByCost();

}
