package com.g7.oam.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int medicineId;

	@ApiModelProperty(name = "Medicine Name", value = "Medicine Name consists of alphanumeric characters and special symbols like {'-','+'}")
	@Size(min = 4, max = 15, message = "Please enter a valid medicine name, Medicine name should be from 4 to 15 characters long.")
	@Pattern(regexp = "^[A-z0-9+-]+", message = "Please enter a valid Medicine name.")
	@Column
	private String medicineName;

	@ApiModelProperty(name = "Medicine cost", value = "Medicine Cost is numeric value representing the cost of the medicine")
	@Pattern(regexp = "[1-9]*", message = "Please enter a valid Medicine Cost that is a number!")
	@Max(10000)
	@Column
	private float medicineCost;

	@ApiModelProperty(name = "Medicine Mfd", value = "Medicine Mfd consists of numeric value for date with delimiter '-")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate mfd;

	@ApiModelProperty(name = "Medicine Expiry Date", value = "Medicine Expiry Date consists of numeric value for date with delimiter '-")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate expiryDate;

	@Column
	@Enumerated(EnumType.STRING)
	private Company company;
	
	@OneToOne
	@JoinColumn(name = "Category_ID", referencedColumnName = "categoryId")
	private Category category;

	public Medicine() {
		super();
	}

	public Medicine(int medicineId, String medicineName, float medicineCost, LocalDate mfd, LocalDate expiryDate,
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + medicineId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicine other = (Medicine) obj;
		if (medicineId != other.medicineId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medicine [medicineId=" + medicineId + ", medicineName=" + medicineName + ", medicineCost="
				+ medicineCost + ", mfd=" + mfd + ", expiryDate=" + expiryDate + ", company=" + company + ", category="
				+ category + "]";
	}

}