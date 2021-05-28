package com.g7.oam.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

	@Autowired
	IOrderRepository orderRepository;
	
	@Autowired
	IMedicineRepository medicineRepository;
	
	@Autowired
	ICustomerRepository customerRepository;

	@Override
	@Transactional
	public Order addOrder(Order order) {
		try {
			orderRepository.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	@Transactional
	public Order updateOrder(Order order) throws OrderNotFoundException {
		Optional<Order> optional = null;
		try {
			optional = orderRepository.findById(order.getOrderId());
			orderRepository.save(order);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new OrderNotFoundException("Order not found for updation!");
			}
		}
		return optional.get();
	}

	@Override
	public Order viewOrder(Order order) throws OrderNotFoundException {
		Optional<Order> optional = null;
		try {
			optional = orderRepository.findById(order.getOrderId());
			orderRepository.findById(order.getOrderId());
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new OrderNotFoundException("Order not found!");
			}
		}
		return optional.get();
	}

	@Override
	@Transactional
	public Order cancelOrder(int orderId) throws OrderNotFoundException {
		Optional<Order> optional = null;
		try {
			optional = orderRepository.findById(orderId);
			orderRepository.deleteById(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new OrderNotFoundException("Order not found for cancellation!");
			}
		}
		return optional.get();
	}

	@Override
	public List<Order> showAllOrders(int medicineId) throws MedicineNotFoundException {
		List<Order> orderList = null;
		List<Order> medicineOrderList = new ArrayList<>();
		Optional<Medicine> optional = null;
		optional = medicineRepository.findById(medicineId);
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
		Optional<Customer> optional = null;
		optional = customerRepository.findById(customer.getUserId());
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
			e.printStackTrace();
		}
		return allOrderList;
	}

	@Override
	public float calculateTotalCost(int orderId) throws OrderNotFoundException {
		float cost = 0;
		Optional<Order> optional = null;
		optional = orderRepository.findById(orderId);
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
