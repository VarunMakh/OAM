package com.g7.oam.service;

import java.util.List;

import com.g7.oam.entities.Medicine;
import com.g7.oam.exception.MedicineNotFoundException;

public interface IMedicineService {

	public Medicine addMedicine(Medicine medicine);

	public Medicine updateMedicine(Medicine medicine) throws MedicineNotFoundException;

	public Medicine viewMedicine(Medicine medicine) throws MedicineNotFoundException;

	public Medicine deleteMedicine(int medicineId) throws MedicineNotFoundException;

	public List<Medicine> showAllMedicine();

}