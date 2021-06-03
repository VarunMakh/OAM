package com.g7.oam.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "Customer_Order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	
	@ApiModelProperty(name = "Order Date", value = "Order Date is system generated for the current date.")
	@Column
	private LocalDate orderDate;
	
	@ApiModelProperty(name = "Medicine List", value = "Medicine List is a list of medicines associated with an Order.")
	@ManyToMany
	@JoinTable(name = "Order_Medicine", joinColumns = @JoinColumn(name = "Order_ID", referencedColumnName = "orderId"), inverseJoinColumns = @JoinColumn(name = "Medicine_ID", referencedColumnName = "medicineId"))
	private List<Medicine> medicineList;
	
	@ApiModelProperty(name = "Dispatch Date", value = "Dispatch Date is system generated for 3 days from the current date.")
	@Column
	private LocalDate dispatchDate;
	
	@ApiModelProperty(name = "Total Cost", value = "Total Cost is system generated to calculate the total cost from the Medicine List.")
	@Column
	private float totalCost;
	
	@ApiModelProperty(name = "User ID", value = "User ID is the Customer who placed the order, it must be a valid User ID.")
	@OneToOne
	@JoinColumn(name = "User_ID", referencedColumnName = "userId")
	private Customer customer;
	
	@ApiModelProperty(name = "Order Status", value = "Order Status is an enumerated object, referenced with alphabetical characters only.")
	@Column
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Order() {
		super();
	}

	public Order(int orderId, LocalDate orderDate, List<Medicine> medicineList, LocalDate dispatchDate, float totalCost,
			Customer customer, OrderStatus status) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId != other.orderId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", medicineList=" + medicineList
				+ ", dispatchDate=" + dispatchDate + ", totalCost=" + totalCost + ", customer=" + customer + ", status="
				+ status + "]";
	}

}
