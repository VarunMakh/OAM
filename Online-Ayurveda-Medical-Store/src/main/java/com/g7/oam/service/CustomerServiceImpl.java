package com.g7.oam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.g7.oam.entities.Customer;
import com.g7.oam.entities.User;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.repository.ICustomerRepository;

public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository repository;

	@Override
	public List<User> showAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer addCustomer(Customer customer) {
		try {
			repository.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> optional = null;
		try {
			optional = repository.findById(customer.getUserId());
			repository.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new CustomerNotFoundException("Customer not found for updation!");
			}
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return optional.get();
	}

	@Override
	public Customer viewCustomer(Customer customer) throws CustomerNotFoundException {
		try {
			repository.findById(customer.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomerNotFoundException("Customer not found!");
		}
		// TODO Auto-generated method stub
		return customer;
	}

	@Override
	public Customer deleteCustomer(int customerId) throws CustomerNotFoundException {
		Optional<Customer> optional = null;
		try {
			optional = repository.findById(customerId);
			repository.deleteById(customerId);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new CustomerNotFoundException("Customer not found for deletion!");
			}
		}
		// TODO Auto-generated method stub
		return optional.get();
	}

	@Override
	public List<Customer> showAllCustomers() {
		List<Customer> customerList = null;
		try {
			customerList = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return customerList;
	}

}