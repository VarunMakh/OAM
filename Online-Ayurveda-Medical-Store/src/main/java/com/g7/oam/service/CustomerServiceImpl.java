package com.g7.oam.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.Customer;
import com.g7.oam.entities.User;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository repository;

	@Override
	public List<User> showAllUsers() {
		return null;
	}

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
		try {
			optional = repository.findById(customer.getUserId());
			repository.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new CustomerNotFoundException("Customer not found for updation!");
			}
		}
		return optional.get();
	}

	@Override
	public Customer viewCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> optional = null;
		try {
			optional = repository.findById(customer.getUserId());
			repository.findById(customer.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new CustomerNotFoundException("Customer not found!");
			}
		}
		return optional.get();
	}

	@Override
	@Transactional
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
		return optional.get();
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
