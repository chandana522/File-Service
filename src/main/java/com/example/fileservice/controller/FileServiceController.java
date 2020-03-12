package com.example.fileservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.fileservice.document.Customer;
import com.example.fileservice.document.Employee;
import com.example.fileservice.document.EmployeeAddress;
import com.example.fileservice.model.FileServiceModel;
import com.example.fileservice.service.CustomerService;
import com.example.fileservice.service.EmployeeAddressService;
import com.example.fileservice.service.EmployeeService;
import com.example.fileservice.service.FileService;

@RestController
@Component
public class FileServiceController {

	@Autowired
	private FileService fileService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeAddressService employeeAddressService;
	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/jsonObject")
	public String jsonObject(@RequestBody FileServiceModel fileServiceModel) {
		String jsonObject = fileService.createJsonObject(fileServiceModel);
		return jsonObject;
	}

	@PostMapping(value = "/jsontoMongoDB", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String insertToMongoDB(@RequestBody List<Customer> customerDetails) {
		customerService.insertJsonToMongoDB(customerDetails);
		return "Customer Doc Created";
	}

	@RequestMapping(value = "/allEmployees", method = RequestMethod.GET)
	public List<Employee> getAllEmployees() {
		List<Employee> result = employeeService.getAllEmployeesDetails();
		return result;
	}

	@GetMapping(value = "/jsonToPdfReport")
	public String getAllCustomersInPDF(Model model) {
		String htmlToString = customerService.getAllCustomerDetailsinPDF();
		return htmlToString;
	}

	@RequestMapping(value = "/allEmployeesBySalaryCondition", method = RequestMethod.GET)
	public List<Employee> getAllEmployeesSalary() {
		List<Employee> result = employeeService.getAllEmployeesWithSalaryCondition();
		return result;
	}

	@PutMapping(value = "/allUpdatedEmployees")
	public EmployeeAddress updateObjectId(@RequestBody Employee employee,
			@RequestBody EmployeeAddress employeeAddress) {
		EmployeeAddress EmpAddress = employeeAddressService.updateEmployeeAddressObjectId(employee, employeeAddress);
		return EmpAddress;
	}

}
