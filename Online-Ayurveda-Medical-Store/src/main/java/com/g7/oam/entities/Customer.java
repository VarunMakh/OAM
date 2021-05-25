package com.g7.oam.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "3")
public class Customer extends User {

	@Column
	private String customerName;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int userId, String password) {
		super(userId, password, "customer");
		// TODO Auto-generated constructor stub
	}

	public Customer(int userId, String password, String customerName) {
		super(userId, password, "customer");
		this.customerName = customerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + "]";
	}

}