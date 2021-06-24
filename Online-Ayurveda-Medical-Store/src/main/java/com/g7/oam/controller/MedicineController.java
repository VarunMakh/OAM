package com.g7.oam.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/medicines")
@Api(value = "OAM - Medicine")
public class MedicineController {

	@Autowired
	IMedicineService medicineService;
	Logger logger = Logger.getLogger(MedicineController.class.getName());

	@PostMapping("/add")
	@ApiOperation(value = "Add Medicine using Post Mapping", response = Medicine.class)
	public ResponseEntity<Medicine> addMedicine(@RequestBody @Valid Medicine medicine) {

		logger.info("Add Medicine Called in Customer Controller");
		Medicine savedMedicine = this.medicineService.addMedicine(medicine);
		return new ResponseEntity<>(savedMedicine, HttpStatus.OK);

	}

	@PutMapping("/update")
	@ApiOperation(value = "Update Medicine using Put Mapping", response = Medicine.class)
	public ResponseEntity<Medicine> updateMedicine(@RequestBody @Valid Medicine medicine)
			throws MedicineNotFoundException {

		logger.info("Update Medicine Called in Customer Controller");
		Medicine updatedMedicine = this.medicineService.updateMedicine(medicine);
		return new ResponseEntity<>(updatedMedicine, HttpStatus.OK);

	}

	@GetMapping("/view")
	@ApiOperation(value = "View Medicine using Get Mapping", response = Medicine.class)
	public ResponseEntity<Medicine> viewMedicine(@RequestBody @Valid Medicine medicine)
			throws MedicineNotFoundException {

		logger.info("View Medicine Called in Customer Controller");
		Medicine retrivedMedicine = this.medicineService.viewMedicine(medicine);
		return new ResponseEntity<>(retrivedMedicine, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{medicineId}")
	@ApiOperation(value = "Delete Medicine using Delete Mapping", response = Medicine.class)
	public ResponseEntity<Medicine> deleteMedicine(@PathVariable("medicineId") int medicineId)
			throws MedicineNotFoundException {

		logger.info("Delete Medicine Called in Customer Controller");
		Medicine deletedMedicine = this.medicineService.deleteMedicine(medicineId);
		return new ResponseEntity<>(deletedMedicine, HttpStatus.OK);

	}

	@GetMapping("/showAll")
	@ApiOperation(value = "Show All Medicine using Get Mapping", response = Medicine.class)
	public ResponseEntity<List<Medicine>> showAllMedicine() {

		logger.info("ShowAll Medicine Called in Customer Controller");
		List<Medicine> medicineList = this.medicineService.showAllMedicines();
		return new ResponseEntity<>(medicineList, HttpStatus.OK);

	}
	
	@GetMapping("/showAllByCost")
	@ApiOperation(value = "Show All Medicines By Company using Get Mapping", response = Medicine.class)
	public ResponseEntity<List<Medicine>> showAllMedicinesByCost() {

		logger.info("ShowAll Medicine Called in Customer Controller");
		List<Medicine> medicineList = this.medicineService.sortMedicinesByCost();
		return new ResponseEntity<>(medicineList, HttpStatus.OK);

	}

}
