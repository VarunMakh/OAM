package com.g7.oam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.entities.Medicine;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.service.IMedicineService;

@RestController
@RequestMapping("/medicines")
public class MedicineController {
	
	@Autowired
	IMedicineService medicineService;
	
	@PostMapping("/add")
	public Medicine addMedicine(@RequestBody Medicine medicine) {
		this.medicineService.addMedicine(medicine);
		return medicine;
	}
	@GetMapping("/view/{medicineId}")	
	public Medicine viewMedicine(@PathVariable("medicineId")Medicine medicine) throws MedicineNotFoundException{
		return this.medicineService.viewMedicine(medicine);
		
		
	}
	@PutMapping("/update")
	public Medicine updateMedicine(@RequestBody Medicine medicine) throws MedicineNotFoundException{
		return this.medicineService.updateMedicine(medicine);
	}
	
	@DeleteMapping("/medicine/{medicineId}")
	public Medicine deleteMedicine(@PathVariable("medicineId") String medicineId) throws MedicineNotFoundException{
		return this.medicineService.deleteMedicine(medicineId) ;
		
	}
	
	@GetMapping("/showAll")
	public List<Medicine> showAllMedicine(){
		return this.medicineService.showAllMedicine();
		
	}
}
