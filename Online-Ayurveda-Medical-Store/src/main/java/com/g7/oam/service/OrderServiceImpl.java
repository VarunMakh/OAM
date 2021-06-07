package com.g7.oam.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.Customer;
import com.g7.oam.entities.Medicine;
import com.g7.oam.entities.Order;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.exception.OrderNotFoundException;
import com.g7.oam.repository.ICustomerRepository;
import com.g7.oam.repository.IMedicineRepository;
import com.g7.oam.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	IOrderRepository orderRepository;

	@Autowired
	IMedicineRepository medicineRepository;

	@Autowired
	ICustomerRepository customerRepository;

	public OrderServiceImpl(IOrderRepository repository) {
		this.orderRepository = repository;
	}

	@Override
	@Transactional
	public Order addOrder(Order order) {

		Order savedOrder = null;
		try {
			savedOrder = orderRepository.save(order);
			savedOrder.setTotalCost(calculateTotalCost(savedOrder.getOrderId()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return savedOrder;

	}

	@Override
	@Transactional
	public Order updateOrder(Order order) throws OrderNotFoundException {

		Optional<Order> optional = orderRepository.findById(order.getOrderId());
		if (optional.isPresent()) {
			orderRepository.save(order);
			return optional.get();
		} else {
			throw new OrderNotFoundException("Order not found for updation!");
		}

	}

	@Override
	public Order viewOrder(Order order) throws OrderNotFoundException {

		Optional<Order> optional = orderRepository.findById(order.getOrderId());
		if (optional.isPresent()) {
			return optional.get();

		} else {
			throw new OrderNotFoundException("Order not found!");
		}

	}

	@Override
	@Transactional
	public Order cancelOrder(int orderId) throws OrderNotFoundException {

		Optional<Order> optional = orderRepository.findById(orderId);
		if (optional.isPresent()) {
			orderRepository.deleteById(orderId);
			return optional.get();
		} else {
			throw new OrderNotFoundException("Order not found for cancellation!");
		}

	}

	@Override
	public List<Order> showAllOrders(int medicineId) throws MedicineNotFoundException {

		List<Order> orderList = null;
		List<Order> medicineOrderList = new ArrayList<>();
		Optional<Medicine> optional = medicineRepository.findById(medicineId);

		if (optional.isPresent()) {
			orderList = orderRepository.findAll();
			for (Order ol : orderList) {
				List<Medicine> medicineList = ol.getMedicineList();
				for (Medicine medicine : medicineList) {
					if (medicine.getMedicineId() == medicineId)
						medicineOrderList.add(ol);
				}
			}
			return medicineOrderList;
		} else {
			throw new MedicineNotFoundException("Medicine not found!");
		}

	}

	@Override
	public List<Order> showAllOrders(Customer customer) throws CustomerNotFoundException {

		List<Order> orderList = null;
		List<Order> customerOrderList = new ArrayList<>();

		Optional<Customer> optional = customerRepository.findById(customer.getUserId());
		if (optional.isPresent()) {
			orderList = orderRepository.findAll();
			for (Order ol : orderList) {
				if (ol.getCustomer().equals(customer)) {
					customerOrderList.add(ol);
				}
			}
			return customerOrderList;
		} else {
			throw new CustomerNotFoundException("Customer not found!");
		}

	}

	@Override
	public List<Order> showAllOrders(LocalDate date) {

		List<Order> orderList = null;
		List<Order> allOrderList = new ArrayList<>();

		try {
			orderList = orderRepository.findAll();
			for (Order ol : orderList) {
				if (ol.getOrderDate().equals(date)) {
					allOrderList.add(ol);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return allOrderList;

	}

	@Override
	public float calculateTotalCost(int orderId) throws OrderNotFoundException {

		float cost = 0;
		Optional<Order> optional = orderRepository.findById(orderId);

		if (optional.isPresent()) {
			List<Medicine> medicineList = optional.get().getMedicineList();
			for (Medicine meds : medicineList) {
				cost += meds.getMedicineCost();
			}
			optional.get().setTotalCost(cost);
			orderRepository.save(optional.get());
			return optional.get().getTotalCost();
		} else {
			throw new OrderNotFoundException("Order not found to calculate cost!");
		}
	}

}
