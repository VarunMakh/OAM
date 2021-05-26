package com.g7.oam.controller;

import java.time.LocalDate;
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
import com.g7.oam.entities.Order;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.exception.OrderNotFoundException;
import com.g7.oam.service.IOrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	IOrderService orderService;
	
	@PostMapping("/add")
	public Order addOrder(@RequestBody Order order) {
		this.orderService.addOrder(order);
		return order;
	}
	
	@GetMapping("/view/{userId}")
	public Order viewOrder(@PathVariable("userId") Order order) throws OrderNotFoundException{
		return this.orderService.viewOrder(order);
	}
	
	@PutMapping("/update")
	public Order updateOrder(@RequestBody Order order) throws OrderNotFoundException{
		return this.orderService.updateOrder(order);
	}
	
	@DeleteMapping("/cancel")
	public Order cancelOrder(@RequestBody int orderId) throws OrderNotFoundException{
		return this.orderService.cancelOrder(orderId);
	}
	
	@GetMapping("/viewall-orders/medicine")
	public List<Order> showAllOrders(@RequestBody String medicineid) throws MedicineNotFoundException{
		return this.orderService.showAllOrders(medicineid);
	}
	
	@GetMapping("/viewall-orders/customer")
	public List<Order> showAllOrders(@RequestBody Customer customer) throws CustomerNotFoundException{
		return this.orderService.showAllOrders(customer);
	}
	
	@GetMapping("/viewall-orders/date")
	public List<Order> showAllOrders(@RequestBody LocalDate date){
		return this.orderService.showAllOrders(date);
	}
	@GetMapping("/calculatecost/{userId}")
	public float calculateTotalCost(@PathVariable("userId") int orderid) throws OrderNotFoundException{
		return this.orderService.calculateTotalCost(orderid);
	}
}
