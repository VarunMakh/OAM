package com.g7.oam.dto;

import java.time.LocalDate;

import com.g7.oam.entities.Category;
import com.g7.oam.entities.Company;



public class MedicineDTO {

	private int medicineId;
	private String medicineName;
	private float medicineCost;
	private LocalDate mfd;
	private LocalDate expiryDate;
	private Company company;
	private Category category;
	
	
	public MedicineDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MedicineDTO(int medicineId, String medicineName, float medicineCost, LocalDate mfd, LocalDate expiryDate,
			Company company, Category category) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCost = medicineCost;
		this.mfd = mfd;
		this.expiryDate = expiryDate;
		this.company = company;
		this.category = category;
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
	public LocalDate getMfd() {
		return mfd;
	}
	public void setMfd(LocalDate mfd) {
		this.mfd = mfd;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	

}
