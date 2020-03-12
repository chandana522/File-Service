package com.example.fileservice.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fileservice.document.Employee;
import com.example.fileservice.repository.EmployeeRepository;
import com.example.fileservice.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployeesWithSalaryCondition() {

		List<Employee> employeeListOfSalary = employeeRepository.findAll();
		List<Employee> filteredEmployeeSalaryList = employeeListOfSalary.stream()
				.filter(employee -> employee.getEmployee_salary() > 10000).collect(Collectors.toList());
		return filteredEmployeeSalaryList;

	}

	@Override
	public List<Employee> getAllEmployeesDetails() {
		return employeeRepository.findAll();
	}

}
