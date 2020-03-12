package com.example.fileservice.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Document("EmployeeAddress")
public class EmployeeAddress {

	public EmployeeAddress() {
	}

	@Id
	@JsonIgnore
	private String id;
	@Field("emp_id")
	private String emp_id;
	@Field("address_type")
	private String address_type;
	@Field("city")
	private String city;
	@Field("state")
	private String state;
	@Field("pincode")
	private String pincode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmp_id() {
		return emp_id;
	}

	@JsonSetter("Employee_ID")
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getAddress_type() {
		return address_type;
	}

	@JsonSetter("Address_Type")
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}

	public String getCity() {
		return city;
	}

	@JsonSetter("City")
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	@JsonSetter("State")
	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	@JsonSetter("Pincode")
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public EmployeeAddress(String emp_id, String address_type, String city, String state, String pincode) {
		super();
		this.emp_id = emp_id;
		this.address_type = address_type;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

}
