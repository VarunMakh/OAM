package com.g7.oam.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerException(CustomerNotFoundException e) {
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Trying to get a Customer...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(e.getMessage();
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<String> handleCategoryException(CategoryNotFoundException e) {
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Trying to get a Category...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(e.getMessage());
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<String> handleOrderException(OrderNotFoundException e) {
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Trying to get a Category...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(e.getMessage());
	}

}
