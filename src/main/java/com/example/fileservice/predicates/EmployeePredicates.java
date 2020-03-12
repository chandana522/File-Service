package com.example.fileservice.predicates;

import java.util.function.Predicate;

import com.example.fileservice.document.Employee;

public class EmployeePredicates {

	public static Predicate<Employee> isSalaryLessThanTenThousand() {
		return (employee -> employee.getEmployee_salary() > 10000);
	}
}
