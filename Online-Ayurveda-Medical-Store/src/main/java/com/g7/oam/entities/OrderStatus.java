package com.g7.oam.entities;

public enum OrderStatus {

	PLACED("Order was successfully placed"), DISPATCHED("Out for delivery"),
	DELAYED("Order has been delayed due to unforeseen circumstances"), DELIVERED("Order was successfully delivered");

	String name;

	OrderStatus(String name) {
		this.name = name;
	}

}
