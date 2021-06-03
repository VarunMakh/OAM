package com.g7.oam.exception;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	String description = "Description";

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerException(CustomerNotFoundException e) {
		HttpHeaders header = new HttpHeaders();
		header.add(description, "Trying to get a Customer...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(e.getMessage());
	}

	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<String> handleAdminException(AdminNotFoundException e) {
		HttpHeaders header = new HttpHeaders();
		header.add(description, "Trying to get an Admin...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(e.getMessage());
	}

	@ExceptionHandler(MedicineNotFoundException.class)
	public ResponseEntity<String> handleMedicineException(MedicineNotFoundException e) {
		HttpHeaders header = new HttpHeaders();
		header.add(description, "Trying to get a Medicine...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(e.getMessage());
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<String> handleCategoryException(CategoryNotFoundException e) {
		HttpHeaders header = new HttpHeaders();
		header.add(description, "Trying to get a Category...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(e.getMessage());
	}

	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<String> handleOrderException(OrderNotFoundException e) {
		HttpHeaders header = new HttpHeaders();
		header.add(description, "Trying to get an Order...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(e.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleViolationException(OrderNotFoundException e) {
		HttpHeaders header = new HttpHeaders();
		header.add(description, "Trying to get an Order...");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(e.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> map = new HashMap<>();

		e.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();

			map.put(fieldName, msg);
		});
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

	}

}
