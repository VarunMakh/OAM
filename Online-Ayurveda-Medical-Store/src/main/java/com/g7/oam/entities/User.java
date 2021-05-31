package com.g7.oam.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "OAM_User")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_mode", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@ApiModelProperty(name="User password",value="User Password consists of alphanumeric characters and special symbols lile {'-','+'}")
	@NotEmpty(message = "User password cannot be empty.")
	@Size(min=4,max=15,message="Please enter a valid User password, User password should be from 4 to 15 characters long.")
	@Pattern(regexp="[A-za-z0-9-+]+",message="Please enter a valid User password.")
	@Column
	private String password;

	@ApiModelProperty(name="User Type",value="User Type consists of alphabets")
	@NotEmpty(message = "User Type cannot be empty.")
	@Pattern(regexp="[A-za-z0-9-+]+",message="Please enter a valid User Type.")
	@Column
	private String userType;

	public User() {
		super();
	}

	public User(int userId, String password, String userType) {
		super();
		this.userId = userId;
		this.password = password;
		this.userType = userType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
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
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", userType=" + userType + "]";
	}

}
