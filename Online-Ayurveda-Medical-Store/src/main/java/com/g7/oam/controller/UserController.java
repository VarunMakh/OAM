package com.g7.oam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.entities.Customer;
import com.g7.oam.entities.User;
import com.g7.oam.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	IUserService userService;

	@GetMapping("/showAll")
	public List<User> showAllUsers() {
		return this.userService.showAllUsers();
	}
}
