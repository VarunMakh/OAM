package com.g7.oam.controller;

import java.util.List;

import javax.validation.Valid;

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
	public Customer addCustomer(@RequestBody @Valid Customer customer) {
		this.customerService.addCustomer(customer);
		return customer;
	}

	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody @Valid Customer customer) throws CustomerNotFoundException {
		return this.customerService.updateCustomer(customer);
	}

	@GetMapping("/view")
	public Customer viewCustomer(@RequestBody @Valid Customer customer) throws CustomerNotFoundException {
		return this.customerService.viewCustomer(customer);
	}

	@DeleteMapping("delete/{userId}")
	public Customer deleteCustomer(@PathVariable("userId") int customerId) throws CustomerNotFoundException {
		return this.customerService.deleteCustomer(customerId);
	}

	@GetMapping("/showAll")
	public List<Customer> showAllCustomers() {
		return this.customerService.showAllCustomers();
	}

}
