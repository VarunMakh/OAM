package com.g7.oam.controller;

import java.util.ArrayList;
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

import com.g7.oam.dto.CustomerDTO;
import com.g7.oam.dto.MedicineDTO;
import com.g7.oam.entities.Customer;
import com.g7.oam.entities.Medicine;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.service.IMedicineService;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

	@Autowired
	IMedicineService medicineService;

	@PostMapping("/add")
public ResponseEntity<MedicineDTO> addMedicine(@RequestBody @Valid Medicine medicine) {
		
		Medicine savedMedicine = this.medicineService.addMedicine(medicine);
		MedicineDTO obj = new MedicineDTO();
		obj.setMedicineId(savedMedicine.getMedicineId());
		obj.setMedicineName(savedMedicine.getMedicineName());
		obj.setMedicineCost(savedMedicine.getMedicineCost());
		obj.setMfd(savedMedicine.getMfd());
		obj.setExpiryDate(savedMedicine.getExpiryDate());
		obj.setCategory(savedMedicine.getCategory());
		obj.setCompany(savedMedicine.getCompany());
		return new ResponseEntity<>(obj, HttpStatus.OK);

	}
	

	@PutMapping("/update")
	public ResponseEntity<MedicineDTO> updateMedicine(@RequestBody @Valid Medicine medicine) throws MedicineNotFoundException{
		Medicine savedMedicine = this.medicineService.updateMedicine(medicine);
		MedicineDTO obj = new MedicineDTO();
		obj.setMedicineId(savedMedicine.getMedicineId());
		obj.setMedicineName(savedMedicine.getMedicineName());
		obj.setMedicineCost(savedMedicine.getMedicineCost());
		obj.setMfd(savedMedicine.getMfd());
		obj.setExpiryDate(savedMedicine.getExpiryDate());
		obj.setCategory(savedMedicine.getCategory());
		obj.setCompany(savedMedicine.getCompany());
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/view")
	public ResponseEntity<MedicineDTO> viewMedicine (@RequestBody @Valid Medicine medicine) throws MedicineNotFoundException{
		Medicine retrivedMedicine = this.medicineService.viewMedicine(medicine);
		MedicineDTO obj = new MedicineDTO();
		obj.setMedicineId(retrivedMedicine.getMedicineId());
		obj.setMedicineName(retrivedMedicine.getMedicineName());
		obj.setMedicineCost(retrivedMedicine.getMedicineCost());
		obj.setMfd(retrivedMedicine.getMfd());
		obj.setExpiryDate(retrivedMedicine.getExpiryDate());
		obj.setCategory(retrivedMedicine.getCategory());
		obj.setCompany(retrivedMedicine.getCompany());
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{medicineId}")
	public ResponseEntity<MedicineDTO> deleteMedicine (@PathVariable ("medicineId") int medicineId) throws MedicineNotFoundException{
		Medicine deletedMedicine = this.medicineService.deleteMedicine(medicineId);
		MedicineDTO obj = new MedicineDTO();
		obj.setMedicineId(deletedMedicine.getMedicineId());
		obj.setMedicineName(deletedMedicine.getMedicineName());
		obj.setMedicineCost(deletedMedicine.getMedicineCost());
		obj.setMfd(deletedMedicine.getMfd());
		obj.setExpiryDate(deletedMedicine.getExpiryDate());
		obj.setCategory(deletedMedicine.getCategory());
		obj.setCompany(deletedMedicine.getCompany());
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@GetMapping("/showAll")
	public ResponseEntity<List<MedicineDTO>> showAllMedicine (){
		List<Medicine> medicineList = this.medicineService.showAllMedicine();
		List<MedicineDTO> medicineDTOList = new ArrayList<>();
		for(Medicine medicine : medicineList) {
			MedicineDTO medicineDTO = new MedicineDTO();
			medicineDTO.setMedicineId(medicine.getMedicineId());
			medicineDTO.setMedicineName(medicine.getMedicineName());
			medicineDTO.setMedicineCost(medicine.getMedicineCost());
			medicineDTO.setMfd(medicine.getMfd());
			medicineDTO.setExpiryDate(medicine.getExpiryDate());
			medicineDTO.setCategory(medicine.getCategory());
			medicineDTO.setCompany(medicine.getCompany());
		}
		
		return new ResponseEntity<>(medicineDTOList, HttpStatus.OK);
	}

}
