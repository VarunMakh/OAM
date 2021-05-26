package com.g7.oam.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.Customer;
import com.g7.oam.entities.Medicine;
import com.g7.oam.entities.Order;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.exception.OrderNotFoundException;
import com.g7.oam.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository repository;

	@Override
	public Order addOrder(Order order) {
		try {
			repository.save(order);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public Order viewOrder(Order order) throws OrderNotFoundException {
		try {
			repository.findById(order.getOrderId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new OrderNotFoundException("Order not found");
		}
		return order;
	}

	@Override
	public Order updateOrder(Order order) throws OrderNotFoundException {
		Optional<Order> optional = null;
		try {
			optional = repository.findById(order.getOrderId());
			repository.save(order);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (optional.get() == null) {
				throw new OrderNotFoundException("Order not found for updation!");
			}
		}
		return optional.get();
	}

	@Override
	public Order cancelOrder(int orderId) throws OrderNotFoundException {
		Optional<Order> optional = null;
		try {
			optional = repository.findById(orderId);
			repository.deleteById(orderId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (optional.get() == null) {
				throw new OrderNotFoundException("Order not found for cancellation!");
			}
		}
		return optional.get();
	}

	@Override
	public List<Order> showAllOrders(String medicineid) throws MedicineNotFoundException {
		List<Order> orderList = null;
		List<Order> allOrderList = new ArrayList<>();
		try {
			orderList = repository.findAll();
			for (Order ol : orderList) {
				List<Medicine> medicineList = ol.getMedicineList();
				for (Medicine medicine : medicineList) {
					if (medicine.getMedicineId() == medicineid)
						allOrderList.add(ol);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new MedicineNotFoundException();
		}
		return allOrderList;
	}

	@Override
	public List<Order> showAllOrders(Customer customer) throws CustomerNotFoundException {

		List<Order> orderList = null;
		List<Order> allOrderList = new ArrayList<>();
		try {
			orderList = repository.findAll();
			for (Order ol : orderList) {
				if (ol.getCustomer() == customer) {
					allOrderList.add(ol);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CustomerNotFoundException();
		}
		return allOrderList;
	}

	@Override
	public List<Order> showAllOrders(LocalDate date) {

		List<Order> orderList = null;
		List<Order> allOrderList = new ArrayList<>();
		try {
			orderList = repository.findAll();
			for (Order ol : orderList) {
				if (ol.getOrderDate() == date) {
					allOrderList.add(ol);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return allOrderList;
	}

	@Override
	public float calculateTotalCost(int orderid) throws OrderNotFoundException {
		float cost = 0;
		try {
			List<Order> orderList = null;
			orderList = repository.findAll();
			for (Order ol : orderList) {
				if (ol.getOrderId() == orderid) {
					cost = ol.getTotalCost();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new OrderNotFoundException();
		}
		return cost;
	}

}
