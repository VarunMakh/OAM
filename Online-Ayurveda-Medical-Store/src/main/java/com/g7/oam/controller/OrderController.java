package com.g7.oam.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.dto.OrderDTO;
import com.g7.oam.entities.Customer;
import com.g7.oam.entities.Medicine;
import com.g7.oam.entities.Order;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.exception.OrderNotFoundException;
import com.g7.oam.service.IOrderService;

@Validated
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	IOrderService orderService;

	@PostMapping("/add")
	public ResponseEntity<OrderDTO> addOrder(@RequestBody @Valid Order order) {
		Order savedOrder = this.orderService.addOrder(order);
		OrderDTO obj = new OrderDTO();
		obj.setOrderId(savedOrder.getOrderId());
		obj.setOrderDate(savedOrder.getOrderDate());
		obj.setMedicineList(savedOrder.getMedicineList());
		obj.setDispatchDate(savedOrder.getDispatchDate());
		obj.setTotalCost(savedOrder.getTotalCost());
		obj.setCustomer(savedOrder.getCustomer());
		obj.setStatus(savedOrder.getStatus());
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<OrderDTO> updateOrder(@RequestBody @Valid Order order) throws OrderNotFoundException {
		Order updatedOrder = this.orderService.updateOrder(order);
		OrderDTO obj = new OrderDTO();
		obj.setOrderId(updatedOrder.getOrderId());
		obj.setOrderDate(updatedOrder.getOrderDate());
		obj.setMedicineList(updatedOrder.getMedicineList());
		obj.setDispatchDate(updatedOrder.getDispatchDate());
		obj.setTotalCost(updatedOrder.getTotalCost());
		obj.setCustomer(updatedOrder.getCustomer());
		obj.setStatus(updatedOrder.getStatus());
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<OrderDTO> viewOrder(@RequestBody @Valid Order order) throws OrderNotFoundException {
		Order retrievedOrder = this.orderService.viewOrder(order);
		OrderDTO obj = new OrderDTO();
		obj.setOrderId(retrievedOrder.getOrderId());
		obj.setOrderDate(retrievedOrder.getOrderDate());
		obj.setMedicineList(retrievedOrder.getMedicineList());
		obj.setDispatchDate(retrievedOrder.getDispatchDate());
		obj.setTotalCost(retrievedOrder.getTotalCost());
		obj.setCustomer(retrievedOrder.getCustomer());
		obj.setStatus(retrievedOrder.getStatus());
		return new ResponseEntity<>(obj, HttpStatus.OK);	}

	@DeleteMapping("/cancel/{orderId}")
	public ResponseEntity<OrderDTO> cancelOrder(@PathVariable("orderId") int orderId) throws OrderNotFoundException {
		Order cancelOrder = this.orderService.cancelOrder(orderId);
		OrderDTO obj = new OrderDTO();
		obj.setOrderId(cancelOrder.getOrderId());
		obj.setOrderDate(cancelOrder.getOrderDate());
		obj.setMedicineList(cancelOrder.getMedicineList());
		obj.setDispatchDate(cancelOrder.getDispatchDate());
		obj.setTotalCost(cancelOrder.getTotalCost());
		obj.setCustomer(cancelOrder.getCustomer());
		obj.setStatus(cancelOrder.getStatus());
		return new ResponseEntity<>(obj, HttpStatus.OK);	}

	@GetMapping("/showAllByMedicine/{medicineId}")
	public ResponseEntity<List<OrderDTO>> showAllOrders(@PathVariable("medicineId") int medicineid) throws MedicineNotFoundException {
		List<Order> orderList = this.orderService.showAllOrders(medicineid);
		List<OrderDTO> orderDtoList = new ArrayList<>();
		
		for(Order order : orderList) {
			OrderDTO orderDto = new OrderDTO();
			orderDto.setOrderId(order.getOrderId());
			orderDto.setOrderDate(order.getOrderDate());
			orderDto.setMedicineList(order.getMedicineList());
			orderDto.setDispatchDate(order.getDispatchDate());
			orderDto.setTotalCost(order.getTotalCost());
			orderDto.setCustomer(order.getCustomer());
			orderDto.setStatus(order.getStatus());
			orderDtoList.add(orderDto);
		}
		return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
	}

	@GetMapping("/showAllByCustomer")
	public ResponseEntity<List<OrderDTO>> showAllOrders(@RequestBody @Valid Customer customer) throws CustomerNotFoundException {
		List<Order> orderList = this.orderService.showAllOrders(customer);
		List<OrderDTO> orderDtoList = new ArrayList<>();
		
		for(Order order : orderList) {
			OrderDTO orderDto = new OrderDTO();
			orderDto.setOrderId(order.getOrderId());
			orderDto.setOrderDate(order.getOrderDate());
			orderDto.setMedicineList(order.getMedicineList());
			orderDto.setDispatchDate(order.getDispatchDate());
			orderDto.setTotalCost(order.getTotalCost());
			orderDto.setCustomer(order.getCustomer());
			orderDto.setStatus(order.getStatus());
			orderDtoList.add(orderDto);
		}
		return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
		//return this.orderService.showAllOrders(customer);
	}

	@GetMapping("/showAllByDate/{orderDate}")
	public ResponseEntity<List<OrderDTO>> showAllOrders(
			@PathVariable("orderDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		List<Order> orderList = this.orderService.showAllOrders(date);
		List<OrderDTO> orderDtoList = new ArrayList<>();
		
		for(Order order : orderList) {
			OrderDTO orderDto = new OrderDTO();
			orderDto.setOrderId(order.getOrderId());
			orderDto.setOrderDate(order.getOrderDate());
			orderDto.setMedicineList(order.getMedicineList());
			orderDto.setDispatchDate(order.getDispatchDate());
			orderDto.setTotalCost(order.getTotalCost());
			orderDto.setCustomer(order.getCustomer());
			orderDto.setStatus(order.getStatus());
			orderDtoList.add(orderDto);
		}
		return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
	}

	@GetMapping("/calculateCost/{orderId}")
	public float calculateTotalCost(@PathVariable("orderId") int orderid) throws OrderNotFoundException {
		return this.orderService.calculateTotalCost(orderid);
	}
}
