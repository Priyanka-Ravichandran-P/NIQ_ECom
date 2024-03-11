package com.tredence.ecommerce.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
	private String message;

	public ProductResponseDTO(String message) {
		this.message = message;
	}
}
