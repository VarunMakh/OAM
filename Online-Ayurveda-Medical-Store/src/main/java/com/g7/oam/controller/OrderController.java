package com.g7.oam.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

	@GetMapping("/view/{orderId}")
	public Order viewOrder(@PathVariable("orderId") Order order) throws OrderNotFoundException {
		return this.orderService.viewOrder(order);
	}

	@PutMapping("/update")
	public Order updateOrder(@RequestBody Order order) throws OrderNotFoundException {
		return this.orderService.updateOrder(order);
	}

	@DeleteMapping("/cancel/{orderId}")
	public Order cancelOrder(@PathVariable("orderId") int orderId) throws OrderNotFoundException {
		return this.orderService.cancelOrder(orderId);
	}

	@GetMapping("/showAllByMedicine/{medicineId}")
	public List<Order> showAllOrders(@PathVariable("medicineId") int medicineid) throws MedicineNotFoundException {
		return this.orderService.showAllOrders(medicineid);
	}

	@GetMapping("/showAllByCustomer/{userId}")
	public List<Order> showAllOrders(@PathVariable("userId") Customer customer) throws CustomerNotFoundException {
		return this.orderService.showAllOrders(customer);
	}

	@GetMapping("/showAllByDate/{orderDate}")
	public List<Order> showAllOrders(
			@PathVariable("orderDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		return this.orderService.showAllOrders(date);
	}

	@GetMapping("/calculateCost/{orderId}")
	public float calculateTotalCost(@PathVariable("orderId") int orderid) throws OrderNotFoundException {
		return this.orderService.calculateTotalCost(orderid);
	}
}
