package com.g7.oam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.service.IMedicineService;

@RestController
public class MedicineController {
	
	@Autowired
	IMedicineService medicineService;

}
