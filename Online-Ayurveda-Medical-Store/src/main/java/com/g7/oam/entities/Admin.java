package com.g7.oam.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "2")
public class Admin extends User {

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