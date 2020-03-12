package com.example.fileservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.fileservice.document.Employee;
import com.example.fileservice.document.EmployeeAddress;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	void insert(List<EmployeeAddress> employeeAddressListToMongoDB);

}
