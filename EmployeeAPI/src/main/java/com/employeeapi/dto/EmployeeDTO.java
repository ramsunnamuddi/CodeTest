package com.employeeapi.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class EmployeeDTO {
	@NotBlank(message = "Employee id cannot be null or empty")
	private String employeeId ;

	@NotBlank(message = "First name cannot be null or empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be null or empty")
    private String lastName;

    @NotBlank(message = "Email cannot be null or empty")
    @Email(message = "Email must be in a valid format")
    private String email;

    @NotEmpty(message = "Phone numbers cannot be null or empty")
    private List<@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid") String> phone_no;

    @NotBlank(message = "Date of joining cannot be null or empty")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of joining must be in YYYY-MM-DD format")
    private String doj;

    @Positive(message = "Salary must be a positive number")
    private Double salary;



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

	public List<String> getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(List<String> phone_no) {
		this.phone_no = phone_no;
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

	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phone_no=" + phone_no + ", doj=" + doj + ", salary=" + salary + "]";
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	
}
