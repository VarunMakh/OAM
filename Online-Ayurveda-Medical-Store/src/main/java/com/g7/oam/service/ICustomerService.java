package com.g7.oam.service;

import java.util.List;

import com.g7.oam.entities.Customer;
import com.g7.oam.exception.CustomerNotFoundException;

public interface ICustomerService {

	public Customer addCustomer(Customer customer);

	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

	public Customer viewCustomer(Customer customer) throws CustomerNotFoundException;

	public Customer deleteCustomer(int customerId) throws CustomerNotFoundException;

	public List<Customer> showAllCustomers();

}
