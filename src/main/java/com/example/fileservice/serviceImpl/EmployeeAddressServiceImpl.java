package com.example.fileservice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.fileservice.document.Employee;
import com.example.fileservice.document.EmployeeAddress;
import com.example.fileservice.service.EmployeeAddressService;

@Service
public class EmployeeAddressServiceImpl implements EmployeeAddressService {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public EmployeeAddress updateEmployeeAddressObjectId(Employee employee, EmployeeAddress employeeAddress) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(employee.getId()));
		query.addCriteria(Criteria.where(employee.getEmployee_id()).is(employeeAddress.getEmp_id()));
		Update update = new Update();
		update.set("id", employee.getId());
		return mongoTemplate.findAndModify(query, update, EmployeeAddress.class);
	}

}
