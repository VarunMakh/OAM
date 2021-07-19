package com.g7.oam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.dto.AdminDTO;
import com.g7.oam.entities.Admin;
import com.g7.oam.exception.AdminNotFoundException;
import com.g7.oam.service.IAdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admins")
@Api(value = "OAM - Admin")
public class AdminController {

	@Autowired
	IAdminService adminService;
	Logger logger = Logger.getLogger(AdminController.class.getName());

	@PostMapping("/add")
	@ApiOperation(value = "Add Admin using Post Mapping", response = Admin.class)
	public ResponseEntity<AdminDTO> addAdmin(@RequestBody @Valid Admin admin) {

		logger.info("Add Admin Called in Admin Controller");
		admin.setUserType("admin");
		Admin savedAdmin = this.adminService.addAdmin(admin);
		AdminDTO obj = new AdminDTO();
		obj.setAdminId(savedAdmin.getUserId());
		obj.setAdminName(savedAdmin.getAdminName());
		return new ResponseEntity<>(obj, HttpStatus.OK);

	}

	@PutMapping("/update")
	@ApiOperation(value = "Update Admin using Put Mapping", response = Admin.class)
	public ResponseEntity<AdminDTO> updateAdmin(@RequestBody @Valid Admin admin) throws AdminNotFoundException {

		logger.info("Update Admin Called in Admin Controller");
		Admin updatedAdmin = this.adminService.updateAdmin(admin);
		AdminDTO obj = new AdminDTO();
		obj.setAdminId(updatedAdmin.getUserId());
		obj.setAdminName(updatedAdmin.getAdminName());
		return new ResponseEntity<>(obj, HttpStatus.OK);
		
	}

	@GetMapping("/view")
	@ApiOperation(value = "View Admin using Get Mapping", response = Admin.class)
	public ResponseEntity<AdminDTO> viewAdmin(@RequestBody @Valid Admin admin) throws AdminNotFoundException {

		logger.info("View Admin Called in Admin Controller");
		Admin retriveAdmin = this.adminService.viewAdmin(admin);
		AdminDTO obj = new AdminDTO();
		obj.setAdminId(retriveAdmin.getUserId());
		obj.setAdminName(retriveAdmin.getAdminName());
		return new ResponseEntity<>(obj, HttpStatus.OK);
		
	}

	@DeleteMapping("/delete/{userId}")
	@ApiOperation(value = "Delete Admin using Delete Mapping", response = Admin.class)
	public ResponseEntity<AdminDTO> deleteAdmin(@PathVariable("userId") int adminId) throws AdminNotFoundException {

		logger.info("Delete Admin Called in Admin Controller");
		Admin deleteAdmin = this.adminService.deleteAdmin(adminId);
		AdminDTO obj = new AdminDTO();
		obj.setAdminId(deleteAdmin.getUserId());
		obj.setAdminName(deleteAdmin.getAdminName());
		return new ResponseEntity<>(obj, HttpStatus.OK);
		
	}

	@GetMapping("/showAll")
	@ApiOperation(value = "Show All Admins using Get Mapping", response = Admin.class)
	public ResponseEntity<List<AdminDTO>> showAllAdmin() {

		logger.info("Show All Admins Called in Admin Controller");
		List<Admin> adminList = this.adminService.showAllAdmins();
		List<AdminDTO> adminDtoList = new ArrayList<>();
		for (Admin admin : adminList) {
			AdminDTO adminDto = new AdminDTO();
			adminDto.setAdminId(admin.getUserId());
			adminDto.setAdminName(admin.getAdminName());
			adminDtoList.add(adminDto);
		}
		return new ResponseEntity<>(adminDtoList, HttpStatus.OK);
	}

}