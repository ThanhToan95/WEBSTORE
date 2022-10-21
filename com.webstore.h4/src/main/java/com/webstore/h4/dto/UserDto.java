package com.webstore.h4.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

	@NotEmpty(message = "Username should not be empty")
	private String username;
	@NotEmpty(message = "Password should not be empty")
	@Min(value = 8, message = "Password must be 8 characters or more")
	private String password;
	@NotEmpty(message = "Fullname should not be empty")
	@Size(min = 5, max = 250)
	private String fullname;
	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;
	@NotNull(message = "Phone should not be empty")
	private Integer phone;
	
	
	public UserDto() {
	
	}


	public UserDto(String username, String password, String fullname, String email, Integer phone) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getPhone() {
		return phone;
	}


	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	
}
