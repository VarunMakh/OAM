package com.g7.oam.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@DiscriminatorValue(value = "2")
public class Admin extends User {

	@ApiModelProperty(name = "Admin Name", value = "Admin Name consists of alphabets ")
	@NotEmpty(message = "Admin Name cannot be empty.")
	@Size(min = 3, max = 15, message = "Please enter a valid name between 3-15 characters!")
	@Pattern(regexp = "[A-za-z0-9]+", message = "Please enter a valid Admin Name.")
	@Column
	private String adminName;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int userId, String password) {
		super(userId, password, "admin");
		// TODO Auto-generated constructor stub
	}

	public Admin(int userId, String password, String adminName) {
		super(userId, password, "admin");
		this.adminName = adminName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "Admin [adminName=" + adminName + "]";
	}

}