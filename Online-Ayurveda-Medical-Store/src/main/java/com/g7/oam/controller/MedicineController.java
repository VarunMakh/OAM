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

	@PutMapping("/update")
	public Medicine updateMedicine(@RequestBody Medicine medicine) throws MedicineNotFoundException {
		return this.medicineService.updateMedicine(medicine);
	}

	@GetMapping("/view")
	public Medicine viewMedicine(@RequestBody Medicine medicine) throws MedicineNotFoundException {
		return this.medicineService.viewMedicine(medicine);

	}

	@DeleteMapping("/delete/{medicineId}")
	public Medicine deleteMedicine(@PathVariable("medicineId") int medicineId) throws MedicineNotFoundException {
		return this.medicineService.deleteMedicine(medicineId);

	}

	@GetMapping("/showAll")
	public List<Medicine> showAllMedicine() {
		return this.medicineService.showAllMedicine();

	}

}
