package com.g7.oam.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.Customer;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());

	@Autowired
	ICustomerRepository repository;

	public CustomerServiceImpl(ICustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {

		Customer savedCustomer = null;
		try {
			savedCustomer = repository.save(customer);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return savedCustomer;

	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {

		Optional<Customer> optional = repository.findById(customer.getUserId());
		if (optional.isPresent()) {
			repository.save(customer);
			return optional.get();
		} else {
			throw new CustomerNotFoundException("Customer not found for updation!");
		}

	}

	@Override
	public Customer viewCustomer(Customer customer) throws CustomerNotFoundException {

		Optional<Customer> optional = repository.findById(customer.getUserId());
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomerNotFoundException("Customer not found!");
		}

	}

	@Override
	@Transactional
	public Customer deleteCustomer(int customerId) throws CustomerNotFoundException {

		Optional<Customer> optional = repository.findById(customerId);
		if (optional.isPresent()) {
			repository.deleteById(customerId);
			return optional.get();
		} else {
			throw new CustomerNotFoundException("Customer not found for deletion!");
		}

	}

	@Override
	public List<Customer> showAllCustomers() {

		logger.info("Show All Customers method called");
		List<Customer> customerList = null;
		try {
			customerList = repository.findAll();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return customerList;
	}

}
