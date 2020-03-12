package com.example.fileservice.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Document("customer")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8957917542128494074L;
	@Id
	@JsonIgnore
	private String id;
	@Field("customer_firstname")
	private String customer_FirstName;
	@Field("customer_lastname")
	private String customer_LastName;
	@Field("customer_Age")
	private int customer_Age;
	@Field("customer_mobile")
	private long customer_Mobile;
	@Field("customer_address")
	private String customer_Address;

	public Customer() {
	}

	public Customer(String customer_FirstName, String customer_LastName, int customer_Age, long customer_Mobile,
			String customer_Address) {
		super();
		customer_FirstName = customer_FirstName;
		customer_LastName = customer_LastName;
		customer_Age = customer_Age;
		customer_Mobile = customer_Mobile;
		customer_Address = customer_Address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer_FirstName() {
		return customer_FirstName;
	}

	@JsonSetter("Customer_FirstName")
	public void setCustomer_FirstName(String customer_FirstName) {
		customer_FirstName = customer_FirstName;
	}

	public String getCustomer_LastName() {
		return customer_LastName;
	}

	@JsonSetter("Customer_LastName")
	public void setCustomer_LastName(String customer_LastName) {
		customer_LastName = customer_LastName;
	}

	public int getCustomer_Age() {
		return customer_Age;
	}

	@JsonSetter("Customer_Age")
	public void setCustomer_Age(int customer_Age) {
		customer_Age = customer_Age;
	}

	public long getCustomer_Mobile() {
		return customer_Mobile;
	}

	@JsonSetter("Customer_Mobile")
	public void setCustomer_Mobile(int customer_Mobile) {
		customer_Mobile = customer_Mobile;
	}

	public String getCustomer_Address() {
		return customer_Address;
	}

	@JsonSetter("Customer_Address")
	public void setCustomer_Address(String customer_Address) {
		customer_Address = customer_Address;
	}
}
