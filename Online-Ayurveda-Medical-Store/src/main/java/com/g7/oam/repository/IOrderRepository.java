package com.g7.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g7.oam.entities.Order;

public interface IOrderRepository extends JpaRepository<Order, Integer> {

}
