package com.g7.oam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.entities.Admin;
import com.g7.oam.exception.AdminNotFoundException;
import com.g7.oam.service.IAdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	IAdminService adminService;

	@PostMapping("/add")
	public Admin addAdmin(@RequestBody Admin admin) {
		this.adminService.addAdmin(admin);
		return admin;
	}

	@PutMapping("/update")
	public Admin updateAdmin(@RequestBody Admin admin) throws AdminNotFoundException {
		return this.adminService.updateAdmin(admin);
	}

	@GetMapping("/view/{userId}")
	public Admin viewAdmin(@PathVariable("userId") Admin admin) throws AdminNotFoundException {
		return this.adminService.viewAdmin(admin);
	}

	@DeleteMapping("/delete/{userId}")
	public Admin deleteAdmin(@PathVariable("userId") Admin admin) throws AdminNotFoundException {
		return this.adminService.deleteAdmin(admin.getUserId());
	}

	@GetMapping("/showAll")
	public List<Admin> showAllAdmin() {
		return this.adminService.showAllAdmins();
	}

}