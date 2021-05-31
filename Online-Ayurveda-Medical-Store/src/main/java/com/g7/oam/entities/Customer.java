package com.g7.oam.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@DiscriminatorValue(value = "3")
public class Customer extends User {

	@ApiModelProperty(name = "Customer Name", value = "Customer name consists of 3-15 alphanumeric characters.")
	@NotEmpty(message = "Customer Name cannot be empty!")
	@Size(min = 3, max = 15, message = "Please enter a valid name between 3-15 characters!")
	@Pattern(regexp = "[A-za-z0-9]+", message = "Please enter a valid Customer Name.")
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