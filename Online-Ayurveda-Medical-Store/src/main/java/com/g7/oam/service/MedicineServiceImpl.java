package com.g7.oam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.Medicine;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.repository.IMedicineRepository;

@Service
public class MedicineServiceImpl implements IMedicineService {

	@Autowired
	IMedicineRepository repository;

	@Override
	public Medicine addMedicine(Medicine medicine) {
		try {
			repository.save(medicine);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicine;
	}

	@Override
	public Medicine viewMedicine(Medicine medicine) throws MedicineNotFoundException {
		Optional<Medicine> optional = null;
		try {
			optional = repository.findById(medicine.getMedicineId());
			repository.findById(medicine.getMedicineId());
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new MedicineNotFoundException("Medicine not found!");
			}
		}
		return medicine;
	}

	@Override
	public Medicine updateMedicine(Medicine medicine) throws MedicineNotFoundException {
		Optional<Medicine> optional = null;
		try {
			optional = repository.findById(medicine.getMedicineId());
			repository.save(medicine);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new MedicineNotFoundException("Medicine not found for updation!");
			}

		}
		return optional.get();
	}

	@Override
	public List<Medicine> showAllMedicine() {
		List<Medicine> medsList = null;
		try {
			medsList = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medsList;
	}

	@Override
	public Medicine deleteMedicine(String medicineId) throws MedicineNotFoundException {
		Optional<Medicine> optional = null;
		try {
			optional = repository.findById(medicineId);
			repository.deleteById(medicineId);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new MedicineNotFoundException("Medicine not found for deletion");
			}
		}
		return optional.get();
	}

}
