package com.example.fileservice.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fileservice.document.Customer;
import com.example.fileservice.repository.CustomerRepository;
import com.example.fileservice.service.CustomerService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	List<Customer> customerValues = null;

	@Override
	public String insertJsonToMongoDB(List<Customer> customerDetailsListToMongoDB) {
		customerRepository.saveAll(customerDetailsListToMongoDB);
		return "inserted";
	}

	@Override
	public String getAllCustomerDetailsinPDF() {
		List<Customer> customerListforPDF = customerRepository.findAll();
		Document document = new Document();
		String htmlToString = null;
		try {
			OutputStream file = new FileOutputStream(new File("C:\\Users\\chand\\PDF\\customers.pdf"));

			PdfWriter writer = PdfWriter.getInstance(document, file);

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("<html>");
			stringBuilder.append("<body>");
			stringBuilder.append("<div class=\"container\">");
			stringBuilder.append("<table border=\"2\">");
			stringBuilder.append("<tr>");
			stringBuilder.append("<th>FirstName</th>");
			stringBuilder.append("<th>LastName</th>");
			stringBuilder.append("<th>Age</th>");
			stringBuilder.append("<th>Mobile</th>");
			stringBuilder.append("<th>Address</th>");
			stringBuilder.append("</tr>");
			for (Customer customer : customerListforPDF) {
				stringBuilder.append("<tr>");
				stringBuilder.append("<td>");
				stringBuilder.append(customer.getCustomer_FirstName());
				stringBuilder.append("</td>");
				stringBuilder.append("<td>");
				stringBuilder.append(customer.getCustomer_LastName());
				stringBuilder.append("</td>");
				stringBuilder.append("<td>");
				stringBuilder.append(customer.getCustomer_Age());
				stringBuilder.append("</td>");
				stringBuilder.append("<td>");
				stringBuilder.append(customer.getCustomer_Mobile());
				stringBuilder.append("</td>");
				stringBuilder.append("<td>");
				stringBuilder.append(customer.getCustomer_Address());
				stringBuilder.append("</td>");
				stringBuilder.append("</tr>");

			}
			stringBuilder.append("</table>");
			stringBuilder.append("</div>");
			stringBuilder.append("</body>");
			stringBuilder.append("</html>");
			System.out.println(stringBuilder);
			htmlToString = stringBuilder.toString();
			document.open();
			InputStream inputStream = new ByteArrayInputStream(htmlToString.getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, inputStream);
			document.close();
			file.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return htmlToString;

	}

}
