package com.example.fileservice.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.fileservice.document.Employee;
import com.example.fileservice.document.EmployeeAddress;
import com.example.fileservice.model.FileServiceModel;
import com.example.fileservice.repository.EmployeeAddressRepository;
import com.example.fileservice.repository.EmployeeRepository;
import com.example.fileservice.service.FileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Configuration("com.example.fileservice.model.FileServiceModel")
public class FileServiceImpl implements FileService {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeAddressRepository employeeAddressRepository;

	@Override
	public String createJsonObject(FileServiceModel fileServiceModel) {
		String csvFilePath = fileServiceModel.getCsvFilePath();
		String configFilePath = fileServiceModel.getConfigFilePath();
		String documentType = fileServiceModel.getDocumentType();
		Map<String, Integer> jsonVartoIndexMap = new HashMap<String, Integer>();
		StringBuffer jsonObject = new StringBuffer();
		try {
			Scanner scanner = new Scanner(new FileInputStream(new File(csvFilePath)));
			List<String> csvHeaderData = Arrays.asList(scanner.nextLine().split(","));
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File(configFilePath)));
			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				int headerIndex = csvHeaderData.indexOf(key);
				jsonVartoIndexMap.put(value, headerIndex);
			}
			jsonObject.append("[");
			while (scanner.hasNext()) {
				jsonObject.append("{");
				List<String> csvRowData = Arrays.asList(scanner.nextLine().split(","));
				for (String jsonVarName : jsonVartoIndexMap.keySet()) {
					String jsonVariable = CreateJsonVariable(jsonVarName,
							csvRowData.get(jsonVartoIndexMap.get(jsonVarName)));
					jsonObject.append(jsonVariable);
					jsonObject.append(",");

				}
				jsonObject.delete(jsonObject.length() - 1, jsonObject.length());
				jsonObject.append("},");

			}

			jsonObject.delete(jsonObject.length() - 1, jsonObject.length());
			jsonObject.append("]");
			System.out.println(jsonObject.toString());
			insertJsonIntoMongoDB(jsonObject, documentType);

			scanner.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	public void insertJsonIntoMongoDB(StringBuffer jsonObject, String documentType) {

		ObjectMapper objectMapper = new ObjectMapper();
		String json = jsonObject.toString();
		List<EmployeeAddress> employeeAddressListToMongoDB = null;

		try {
			if ("EmployeeAddress".equals(documentType)) {
				employeeAddressListToMongoDB = Arrays.asList(objectMapper.readValue(json, EmployeeAddress[].class));
				employeeAddressRepository.insert(employeeAddressListToMongoDB);
			} else if ("Employee".equals(documentType)) {
				Employee employee = new Employee();
				List<Employee> employeeListToMongoDB = Arrays.asList(objectMapper.readValue(json, Employee[].class));
				employee.setAddresses(employeeAddressListToMongoDB);
				employeeRepository.insert(employeeListToMongoDB);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

	public String CreateJsonVariable(String jsonVarName, String jsonValue) {
		StringBuffer strBuffer = new StringBuffer("\"[a-zA-Z]\":\"[a-zA-Z]\"");

		strBuffer.replace(12, 20, jsonValue);
		System.out.println(jsonValue);
		strBuffer.replace(1, 9, jsonVarName);
		System.out.println(jsonVarName);
		System.out.println(strBuffer.toString());
		return strBuffer.toString();

	}

}
