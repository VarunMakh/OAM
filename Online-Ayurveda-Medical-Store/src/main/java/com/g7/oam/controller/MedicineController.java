package com.g7.oam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Medicine> addMedicine(@RequestBody @Valid Medicine medicine) {

		Medicine savedMedicine = this.medicineService.addMedicine(medicine);
		return new ResponseEntity<>(savedMedicine, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<Medicine> updateMedicine(@RequestBody @Valid Medicine medicine)
			throws MedicineNotFoundException {

		Medicine updatedMedicine = this.medicineService.updateMedicine(medicine);
		return new ResponseEntity<>(updatedMedicine, HttpStatus.OK);

	}

	@GetMapping("/view")
	public ResponseEntity<Medicine> viewMedicine(@RequestBody @Valid Medicine medicine)
			throws MedicineNotFoundException {
		
		Medicine retrivedMedicine = this.medicineService.viewMedicine(medicine);
		return new ResponseEntity<>(retrivedMedicine, HttpStatus.OK);
		
	}

	@DeleteMapping("/delete/{medicineId}")
	public ResponseEntity<Medicine> deleteMedicine(@PathVariable("medicineId") int medicineId)
			throws MedicineNotFoundException {

		Medicine deletedMedicine = this.medicineService.deleteMedicine(medicineId);
		return new ResponseEntity<>(deletedMedicine, HttpStatus.OK);

	}

	@GetMapping("/showAll")
	public ResponseEntity<List<Medicine>> showAllMedicine() {

		List<Medicine> medicineList = this.medicineService.showAllMedicines();
		return new ResponseEntity<>(medicineList, HttpStatus.OK);

	}

}
