package com.g7.oam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.dto.CustomerDTO;
import com.g7.oam.entities.Customer;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.service.ICustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	ICustomerService customerService;

	@PostMapping("/add")
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody @Valid Customer customer) {
		
		customer.setUserType("customer");
		Customer savedCustomer = this.customerService.addCustomer(customer);
		CustomerDTO obj = new CustomerDTO();
		obj.setCustomerId(savedCustomer.getUserId());
		obj.setCustomerName(savedCustomer.getCustomerName());
		return new ResponseEntity<>(obj, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody @Valid Customer customer)
			throws CustomerNotFoundException {

		Customer updatedCustomer = this.customerService.updateCustomer(customer);
		CustomerDTO obj = new CustomerDTO();
		obj.setCustomerId(updatedCustomer.getUserId());
		obj.setCustomerName(updatedCustomer.getCustomerName());
		return new ResponseEntity<>(obj, HttpStatus.OK);

	}

	@GetMapping("/view")
	public ResponseEntity<CustomerDTO> viewCustomer(@RequestBody @Valid Customer customer)
			throws CustomerNotFoundException {
		
		Customer retrievedCustomer = this.customerService.viewCustomer(customer);
		CustomerDTO obj = new CustomerDTO();
		obj.setCustomerId(retrievedCustomer.getUserId());
		obj.setCustomerName(retrievedCustomer.getCustomerName());
		return new ResponseEntity<>(obj, HttpStatus.OK);
		
	}

	@DeleteMapping("delete/{userId}")
	public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable("userId") int customerId)
			throws CustomerNotFoundException {
		
		Customer deletedCustomer = this.customerService.deleteCustomer(customerId);
		CustomerDTO obj = new CustomerDTO();
		obj.setCustomerId(deletedCustomer.getUserId());
		obj.setCustomerName(deletedCustomer.getCustomerName());
		return new ResponseEntity<>(obj, HttpStatus.OK);
		 
	}

	@GetMapping("/showAll")
	public ResponseEntity<List<CustomerDTO>> showAllCustomers() {
		List<Customer> customerList = this.customerService.showAllCustomers();
		List<CustomerDTO> customerDtoList = new ArrayList<>();
		for (Customer customer : customerList) {
			CustomerDTO customerDto = new CustomerDTO();
			customerDto.setCustomerId(customer.getUserId());
			customerDto.setCustomerName(customer.getCustomerName());
			customerDtoList.add(customerDto);
		}
		return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
	}

}
