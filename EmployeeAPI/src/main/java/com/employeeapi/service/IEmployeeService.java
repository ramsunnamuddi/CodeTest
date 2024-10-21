package com.employeeapi.service;

import com.employeeapi.dto.EmployeeDTO;
import com.employeeapi.dto.TaxDeductionDTO;

public interface IEmployeeService {
	public EmployeeDTO saveEmployee(EmployeeDTO dto);
	TaxDeductionDTO taxDeductions(String id);
}
