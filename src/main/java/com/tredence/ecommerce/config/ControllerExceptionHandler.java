package com.tredence.ecommerce.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tredence.ecommerce.exception.ProductNotFoundException;
import com.tredence.ecommerce.exception.ShopperNotFoundException;
import com.tredence.ecommerce.model.ErrorMessage;

@RestControllerAdvice
public class ControllerExceptionHandler {

	
	
	@ExceptionHandler(value = { ProductNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<ErrorMessage> productNotFoundException(Exception ex) {
		ErrorMessage message = new ErrorMessage("not found", ex.getMessage());
		ResponseEntity<ErrorMessage> responseEntity =  ResponseEntity.ok(message);
		return responseEntity;
	}
	
	@ExceptionHandler(value = { ShopperNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<ErrorMessage> ShopperNotFoundException(Exception ex) {
		ErrorMessage message = new ErrorMessage("not found", ex.getMessage());
		ResponseEntity<ErrorMessage> responseEntity =  ResponseEntity.ok(message);
		return responseEntity;
	}
	
	
	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(Exception ex) {
		ErrorMessage message = new ErrorMessage("error", ex.getMessage());
		ResponseEntity<ErrorMessage> responseEntity =  ResponseEntity.ok(message);
		return responseEntity;
	}
}
