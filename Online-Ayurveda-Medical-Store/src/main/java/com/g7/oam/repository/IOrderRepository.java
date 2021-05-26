package com.g7.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g7.oam.entities.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {

}
