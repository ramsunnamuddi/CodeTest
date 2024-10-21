package com.employeeapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employeeapi.dto.EmployeeDTO;
import com.employeeapi.dto.TaxDeductionDTO;
import com.employeeapi.entity.Employee;
import com.employeeapi.exceptions.UserAlreadyExistsException;
import com.employeeapi.exceptions.UserNotFoundException;
import com.employeeapi.repository.IEmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository iEmployeeRepository;
	
	public IEmployeeRepository getiEmployeeRepository() {
		return iEmployeeRepository;
	}

	public void setiEmployeeRepository(IEmployeeRepository iEmployeeRepository) {
		this.iEmployeeRepository = iEmployeeRepository;
	}


	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO dto) {
		Employee emp = new Employee();
		String empId =  dto.getEmployeeId();
		String email = dto.getEmail();
		if(iEmployeeRepository.existsById(empId) || iEmployeeRepository.existsByEmail(email)) throw new UserAlreadyExistsException();
		emp.setEmployeeId(empId);
		emp.setFirstName(dto.getFirstName());
		emp.setLastName(dto.getLastName());
		emp.setSalary(dto.getSalary());
		emp.setDoj(dto.getDoj());
		emp.setEmail(email);
		emp.setPhone_no(dto.getPhone_no());
		Employee savedEmployee = iEmployeeRepository.save(emp);
		dto.setEmployeeId(savedEmployee.getEmployeeId());
		return dto;
	}

	@Override
	public TaxDeductionDTO taxDeductions(String id) {
		double[] taxDeducted = {0.0,0.0};
		TaxDeductionDTO taxDeductionDTO = new TaxDeductionDTO();

		Employee emp =  iEmployeeRepository.findById(id).orElseThrow(()-> new  UserNotFoundException("Sorry, we can't find the user!!!"));

		taxDeductionDTO.setEmployeeId(emp.getEmployeeId());
		taxDeductionDTO.setFirstName(emp.getFirstName());
		taxDeductionDTO.setLastName(emp.getLastName());
		taxDeductionDTO.setYearlySalary(calculateYearlySalary(emp.getDoj(), emp.getSalary()));
		
		taxDeducted  =  calculateTax(taxDeductionDTO.getYearlySalary());
		taxDeductionDTO.setTaxAmount(taxDeducted[0]);
		taxDeductionDTO.setCessAmount(taxDeducted[1]);
		
		return taxDeductionDTO;
	}
	
	private double[] calculateTax(double income) {
		double[] tax = {0.0,0.0};
		 double cess = 0.02 * Math.max(0, income - 2500000);
	        
	        if (income > 1500000) {
	            tax[0] += (income - 1500000) * 0.30; // 30% tax
	            income = 1500000; // Reduce income to 15 lakh for next calculation
	        }

	        if (income > 1250000) {
	        	tax[0] += (income - 1250000) * 0.25; // 25% tax
	            income = 1250000; // Reduce income to 12.5 lakh for next calculation
	        }

	        if (income > 1000000) {
	        	tax[0] += (income - 1000000) * 0.20; // 20% tax
	            income = 1000000; // Reduce income to 10 lakh for next calculation
	        }

	        if (income > 750000) {
	        	tax[0] += (income - 750000) * 0.15; // 15% tax
	            income = 750000; // Reduce income to 7.5 lakh for next calculation
	        }

	        if (income > 500000) {
	        	tax[0] += (income - 500000) * 0.10; // 10% tax
	            income = 500000; // Reduce income to 5 lakh for next calculation
	        }

	        if (income > 250000) {
	        	tax[0] += (income - 250000) * 0.05; // 5% tax
	        }
	       
	        tax[1] = cess;
			
			return tax;
	}
	

    public double calculateYearlySalary(String  dojString, double salary)  {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        double yearlySalary = salary;
	        try {
	        	Date doj = formatter.parse(dojString);
	        	Date today = new Date();
		        
		        Calendar cal = Calendar.getInstance();
		        
		        cal.setTime(doj);
		        int joiningMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH) + 1;
		        cal.add(Calendar.MONTH, 1);
		        cal.set(Calendar.DAY_OF_MONTH, 1);
		        Date startDate = cal.getTime();
		        cal.setTime(today);
		        int todayYear = cal.get(Calendar.YEAR);
		        int todayMonth = cal.get(Calendar.MONTH);
		        
		        cal.setTime(startDate);
		        int startYear = cal.get(Calendar.YEAR);
		        int startMonth = cal.get(Calendar.MONTH);
		        
		        int monthsWorked = (todayYear - startYear) * 12 + (todayMonth - startMonth)+1;
		        yearlySalary = salary * monthsWorked;
		        
		        DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    	LocalDate localDOJ = LocalDate.parse(dojString, dateTimeformatter);
		    	int daysWorkedInJoiningMonth = (int) (ChronoUnit.DAYS.between(localDOJ, localDOJ.withDayOfMonth(localDOJ.lengthOfMonth())) + 1);
		    	YearMonth yearMonth = YearMonth.from(localDOJ);
		    	long daysInMonth = yearMonth.lengthOfMonth();
		    	int lopDays  = (int) (daysInMonth - daysWorkedInJoiningMonth);
		    	
		        double salaryPerMonth = yearlySalary / 12;
		        double dailySalary = salaryPerMonth / daysInMonth;
		        double lopDeduction = dailySalary * lopDays;
		        double adjSalaryPerMonth = salaryPerMonth - lopDeduction;
		        yearlySalary+= adjSalaryPerMonth;
		        
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
        return yearlySalary;
    }
    

}
