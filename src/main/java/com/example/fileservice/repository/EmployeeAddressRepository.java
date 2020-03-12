package com.example.fileservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fileservice.document.EmployeeAddress;

@Repository
public interface EmployeeAddressRepository extends MongoRepository<EmployeeAddress, String> {

	@Query(value = "{'émployees.employee_name':?0}", fields = "{'employees':0}")
	EmployeeAddress findAddressByEmployeeName(String employee_name);

}
