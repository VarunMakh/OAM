package com.g7.oam.dto;

import java.time.LocalDate;
import java.util.List;

import com.g7.oam.entities.OrderStatus;

public class OrderDTO {

	private int orderId;
	private LocalDate orderDate;
	private List<MedicineDTO> medicineDtoList;
	private LocalDate dispatchDate;
	private float totalCost;
	private CustomerDTO customerDto;
	private OrderStatus status;

	public OrderDTO() {
		super();
	}

	public OrderDTO(int orderId, LocalDate orderDate, List<MedicineDTO> medicineDtoList, LocalDate dispatchDate,
			float totalCost, CustomerDTO customerDto, OrderStatus status) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.medicineDtoList = medicineDtoList;
		this.dispatchDate = dispatchDate;
		this.totalCost = totalCost;
		this.customerDto = customerDto;
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

	public List<MedicineDTO> getMedicineDtoList() {
		return medicineDtoList;
	}

	public void setMedicineDtoList(List<MedicineDTO> medicineDtoList) {
		this.medicineDtoList = medicineDtoList;
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

	public CustomerDTO getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDTO customerDto) {
		this.customerDto = customerDto;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
