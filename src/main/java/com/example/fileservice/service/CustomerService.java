package com.example.fileservice.service;

import java.util.List;

import com.example.fileservice.document.Customer;

public interface CustomerService {
	public String insertJsonToMongoDB(List<Customer> customer);

	public String getAllCustomerDetailsinPDF();
}
