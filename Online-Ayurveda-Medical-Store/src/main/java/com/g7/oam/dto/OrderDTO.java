package com.g7.oam.dto;

import java.time.LocalDate;
import java.util.List;

import com.g7.oam.entities.Customer;
import com.g7.oam.entities.Medicine;
import com.g7.oam.entities.OrderStatus;

public class OrderDTO {
	
	private int orderId;
	private LocalDate orderDate;
	private List<Medicine> medicineList;
	private LocalDate dispatchDate;
	private float totalCost;
	private Customer customer;
	private OrderStatus status;
	public OrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDTO(int orderId, LocalDate orderDate, List<Medicine> medicineList, LocalDate dispatchDate,
			float totalCost, Customer customer, OrderStatus status) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.medicineList = medicineList;
		this.dispatchDate = dispatchDate;
		this.totalCost = totalCost;
		this.customer = customer;
		this.status = status;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public List<Medicine> getMedicineList() {
		return medicineList;
	}
	public void setMedicineList(List<Medicine> medicineList) {
		this.medicineList = medicineList;
	}
	public LocalDate getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	
}
