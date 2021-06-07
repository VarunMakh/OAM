package com.g7.oam.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.dto.UserDTO;
import com.g7.oam.entities.Admin;
import com.g7.oam.entities.Customer;
import com.g7.oam.entities.User;
import com.g7.oam.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/users")
@Api(value = "OAM - User")
public class UserController {

	@Autowired
	IUserService userService;
	Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);

	@GetMapping("/showAll")
	@ApiOperation(value = "Show All Users using Get Mapping", response = User.class)
	public ResponseEntity<List<UserDTO>> showAllUsers() {

		logger.info("Show All Users Called in User Controller");
		List<User> userList = this.userService.showAllUsers();
		List<UserDTO> userDtoList = new ArrayList<>();

		for (User user : userList) {

			UserDTO userDto = new UserDTO();
			userDto.setUserId(user.getUserId());
			userDto.setUserType(user.getUserType());

			if (userDto.getUserType().equals("customer")) {
				Customer customer = (Customer) user;
				userDto.setUserName(customer.getCustomerName());
			} else if (userDto.getUserType().equals("admin")) {
				Admin admin = (Admin) user;
				userDto.setUserName(admin.getAdminName());
			}
			userDtoList.add(userDto);
		}

		return new ResponseEntity<>(userDtoList, HttpStatus.OK);
	}
}
