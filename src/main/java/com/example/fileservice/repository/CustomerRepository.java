package com.example.fileservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.fileservice.document.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
