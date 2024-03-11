package com.tredence.ecommerce.exception;

public class ShopperNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public ShopperNotFoundException() {
	}

	public ShopperNotFoundException(String message) {
		this.message = message;
	}
}
