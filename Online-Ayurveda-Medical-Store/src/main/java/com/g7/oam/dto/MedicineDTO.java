package com.g7.oam.dto;

import java.time.LocalDate;

public class MedicineDTO {

	private int medicineId;
	private String medicineName;
	private float medicineCost;
	private LocalDate expiryDate;

	public MedicineDTO() {
		super();
	}

	public MedicineDTO(int medicineId, String medicineName, float medicineCost, LocalDate expiryDate) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCost = medicineCost;
		this.expiryDate = expiryDate;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public float getMedicineCost() {
		return medicineCost;
	}

	public void setMedicineCost(float medicineCost) {
		this.medicineCost = medicineCost;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

}
