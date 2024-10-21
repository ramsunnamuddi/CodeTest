package com.employeeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employeeapi.service.IEmployeeService;
import com.employeeapi.dto.TaxDeductionDTO;
import com.employeeapi.dto.EmployeeDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeAPIController {
	@Autowired
	private IEmployeeService iEmployeeService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveEmployee(@Valid @RequestBody  EmployeeDTO empDto) {
		EmployeeDTO savedEmployee = iEmployeeService.saveEmployee(empDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}
	
	@GetMapping("/{id}/tax-deductions")
	public ResponseEntity<?> taxDeductions(
    @RequestHeader(name = "Authorization" , required=true) String authHeader, @PathVariable String id) throws Exception {
		TaxDeductionDTO taxDeductionDTO= iEmployeeService.taxDeductions(id);
	
		return ResponseEntity.status(HttpStatus.OK).body(taxDeductionDTO);
	}
}
