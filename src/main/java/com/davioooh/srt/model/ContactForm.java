package com.davioooh.srt.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ContactForm {
	@NotEmpty
	@Size(max=50)
	private String firstName;
	@NotEmpty
	@Size(max=100)
	private String lastName;
	@NotEmpty
	@Size(max=20)
	private String phone;
	@NotEmpty
	@Email
	@Size(max=100)
	private String email;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
