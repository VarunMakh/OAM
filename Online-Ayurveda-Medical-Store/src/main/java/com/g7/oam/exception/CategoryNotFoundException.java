package com.g7.oam.exception;

public class CategoryNotFoundException extends Exception {

	public CategoryNotFoundException() {
		super();
	}

	public CategoryNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CategoryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoryNotFoundException(String message) {
		super(message);
	}

	public CategoryNotFoundException(Throwable cause) {
		super(cause);
	}

}
