package com.tredence.ecommerce.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {

	private String category;
	private String brand;
	int limit;
	
}
