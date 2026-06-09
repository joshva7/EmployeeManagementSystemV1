package com.example.emplyee_manager_sysetm.eneitys;

import com.example.emplyee_manager_sysetm.emus.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class EmplyeeEntity {
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	@NotEmpty(message = "Name Filed Not Null")
	private String name;
	@Email(message = "Email Filed not Valid")
	private String email;
	@NotNull(message = "PhoneNumber Filed Not Null")
	private String phoneNumber;
	@NotEmpty(message = "Designation Filed Not Null")
	private String designation;
	@NotNull(message = "Salary Filed Not Null")
	@Min(0)
	private Double salary;
	@NotEmpty(message = "Password filed not Empty")
	private String password;
	@Enumerated(EnumType.STRING)
	private Roles role;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
}
