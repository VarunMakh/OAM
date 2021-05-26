package com.g7.oam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.entities.Customer;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.service.ICustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	ICustomerService customerService;

	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		this.customerService.addCustomer(customer);
		return customer;
	}

	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		return this.customerService.updateCustomer(customer);
	}

	@GetMapping("/view/{userId}")
	public Customer viewCustomer(@PathVariable("userId") Customer customer) throws CustomerNotFoundException {
		return this.customerService.viewCustomer(customer);
	}

	@DeleteMapping("delete/{userId}")
	public Customer deleteCustomer(@PathVariable("userId") Customer customer) throws CustomerNotFoundException {
		return this.customerService.deleteCustomer(customer.getUserId());
	}
	
	@GetMapping("/showAll")
	public List<Customer> showAllCustomers(){
		return this.customerService.showAllCustomers();
	}

}
