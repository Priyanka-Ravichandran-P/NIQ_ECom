package com.tredence.ecommerce.dto;

import lombok.Data;

@Data
public class ShelfResponseEntityDTO {
	private String message;
	
	public ShelfResponseEntityDTO(String message) {
		this.message = message;
	}
}
