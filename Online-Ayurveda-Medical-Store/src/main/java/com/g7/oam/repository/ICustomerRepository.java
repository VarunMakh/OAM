package com.g7.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g7.oam.entities.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	
}
