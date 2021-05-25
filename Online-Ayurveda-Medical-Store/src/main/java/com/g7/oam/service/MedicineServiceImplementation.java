package com.g7.oam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.g7.oam.entities.Customer;
import com.g7.oam.entities.Medicine;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.repository.IMedicineRepository;

public class MedicineServiceImplementation implements IMedicineService {
	@Autowired
	IMedicineRepository repository;
	

	@Override
	public Medicine addMedicine(Medicine medicine) {
		
		try {
			repository.save(medicine);
		}catch(Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return medicine;
	}

	@Override
	public Medicine viewMedicine(Medicine medicine) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new MedicineNotFoundException("Medicine not found");
		}
		return medicine;
	}

	@Override
	public Medicine updateMedicine(Medicine medicine) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
		try {
			repository.save(medicine);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return medicine;
	}

	@Override
	public Medicine deleteMedicine(int medicineId) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
		Medicine meds = new Medicine();
		meds.setMedicineId(String.valueOf(medicineId));
		
		try{
			repository.deleteById(medicineId);
		}catch(Exception e) {
			e.printStackTrace();
			throw new MedicineNotFoundException("Medicine not found");
		}
		return meds;
	}

	@Override
	public List<Medicine> showAllMedicine() {
		// TODO Auto-generated method stub
		List<Medicine> medsList = null;
		try {
			medsList=repository.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return medsList;
	}

}
