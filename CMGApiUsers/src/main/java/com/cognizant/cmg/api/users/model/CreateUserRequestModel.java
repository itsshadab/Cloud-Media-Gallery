package com.cognizant.cmg.api.users.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

	@NotNull(message = "First name cannot be null")
	@Size(min = 2, message = "First name should be greater than 2 characters")
	private String firstName;
	
	@NotNull(message = "Last name cannot be null")
	private String lastName;

	@NotNull(message = "Username cannot be null")
	private String userName;

	@NotNull(message = "Email id cannot be null")
	@Email
	private String emailId;
	
	@NotNull(message = "Password cannot be null")
	@Size(min = 8, max = 16, message = "Password must be greater than 8 characters and less than 16 characters")
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
