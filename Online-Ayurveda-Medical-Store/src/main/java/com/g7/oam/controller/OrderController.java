package com.g7.oam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.service.IOrderService;

@RestController
public class OrderController {
	
	@Autowired
	IOrderService orderService;

}
