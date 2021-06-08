package com.g7.oam.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.Medicine;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.repository.IMedicineRepository;

@Service
public class MedicineServiceImpl implements IMedicineService {

	Logger logger = Logger.getLogger(MedicineServiceImpl.class.getName());

	@Autowired
	IMedicineRepository repository;

	public MedicineServiceImpl(IMedicineRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Medicine addMedicine(Medicine medicine) {

		Medicine savedMedicine = null;
		try {
			savedMedicine = repository.save(medicine);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return savedMedicine;
	}

	@Override
	@Transactional
	public Medicine updateMedicine(Medicine medicine) throws MedicineNotFoundException {

		Optional<Medicine> optional = repository.findById(medicine.getMedicineId());
		if (optional.isPresent()) {
			repository.save(medicine);
			return optional.get();
		} else {
			throw new MedicineNotFoundException("Medicine not found for updation!");
		}
	}

	@Override
	public Medicine viewMedicine(Medicine medicine) throws MedicineNotFoundException {

		Optional<Medicine> optional = repository.findById(medicine.getMedicineId());
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new MedicineNotFoundException("Medicine not found!");
		}

	}

	@Override
	@Transactional
	public Medicine deleteMedicine(int medicineId) throws MedicineNotFoundException {

		Optional<Medicine> optional = repository.findById(medicineId);
		if (optional.isPresent()) {
			repository.deleteById(medicineId);
			return optional.get();
		} else {
			throw new MedicineNotFoundException("Medicine not found for deletion!");
		}

	}

	@Override
	public List<Medicine> showAllMedicines() {

		List<Medicine> medsList = null;
		try {
			medsList = repository.findAll();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return medsList;
	}

}
