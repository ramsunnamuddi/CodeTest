package com.employeeapi.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
	@Id
	@Column(name="employee_id")
	private String employeeId;
	@Column(name="first_name")
    private String firstName;
	@Column(name="last_name")
    private String lastName;
    private String email;
	private String phone_no;
    private String doj;
    private Double salary;
   

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(List<String> phone_no) {
		this.phone_no = String.join(",", phone_no);
	}

	@Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phone_no=" + phone_no + ", doj=" + doj + ", salary=" + salary + "]";
	}

	public String getEmployeeId() {
		return employeeId;
	}
	
	
}
