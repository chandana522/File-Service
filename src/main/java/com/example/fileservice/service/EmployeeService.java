package com.example.fileservice.service;

import java.util.List;

import com.example.fileservice.document.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployeesWithSalaryCondition();

	public List<Employee> getAllEmployeesDetails();

}
