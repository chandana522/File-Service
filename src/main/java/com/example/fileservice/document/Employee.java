package com.example.fileservice.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Document("employee")
public class Employee {
	public Employee() {

	}

	@Id
	@JsonIgnore
	private String id;
	@Field("employee_id")
	private String employee_id;
	@Field("employee_name")
	@Indexed
	private String employee_name;
	@Field("employee_designation")
	private String employee_designation;
	@Field("employee_salary")
	private int employee_salary;
	@Field("employee_insurance")
	private String employee_insurance;

	@DBRef
	private List<EmployeeAddress> addresses;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	@JsonSetter("Employee_ID")
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	@JsonSetter("Employee_Name")
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_designation() {
		return employee_designation;
	}

	@JsonSetter("Employee_Designation")
	public void setEmployee_designation(String employee_designation) {
		this.employee_designation = employee_designation;
	}

	public int getEmployee_salary() {
		return employee_salary;
	}

	@JsonSetter("Employee_Salary")
	public void setEmployee_salary(int employee_salary) {
		this.employee_salary = employee_salary;
	}

	public String getEmployee_insurance() {
		return employee_insurance;
	}

	@JsonSetter("Employee_Insurance")
	public void setEmployee_insurance(String employee_insurance) {
		this.employee_insurance = employee_insurance;
	}

	public List<EmployeeAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<EmployeeAddress> addresses) {
		this.addresses = addresses;
	}

	public Employee(int emp_id, String employee_name, String employee_designation, int employee_salary,
			String employee_insurance, List<EmployeeAddress> addresses) {
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.employee_designation = employee_designation;
		this.employee_salary = employee_salary;
		this.employee_insurance = employee_insurance;
		this.addresses = addresses;
	}

}
