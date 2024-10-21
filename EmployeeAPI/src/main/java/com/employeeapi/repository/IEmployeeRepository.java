package com.employeeapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeapi.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, String> {

	Optional<Employee> findById(String id);

	boolean existsByEmail(String email);

}
