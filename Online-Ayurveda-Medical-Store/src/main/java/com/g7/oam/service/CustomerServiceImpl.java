package com.g7.oam.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.Customer;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository repository;

	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {
		try {
			repository.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> optional = null;
		optional = repository.findById(customer.getUserId());
		if (optional.isPresent()) {
			repository.save(customer);
			return optional.get();
		} else {
			throw new CustomerNotFoundException("Customer not found for updation!");
		}
	}

	@Override
	public Customer viewCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> optional = null;
		optional = repository.findById(customer.getUserId());
		if (optional.isPresent()) {
			repository.findById(customer.getUserId());
			return optional.get();
		} else {
			throw new CustomerNotFoundException("Customer not found!");
		}
	}

	@Override
	@Transactional
	public Customer deleteCustomer(int customerId) throws CustomerNotFoundException {
		Optional<Customer> optional = null;
		optional = repository.findById(customerId);
		if (optional.isPresent()) {
			repository.deleteById(customerId);
			return optional.get();
		} else {
			throw new CustomerNotFoundException("Customer not found for deletion!");
		}
	}

	@Override
	public List<Customer> showAllCustomers() {
		List<Customer> customerList = null;
		try {
			customerList = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}

}
