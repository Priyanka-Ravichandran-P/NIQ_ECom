package com.tredence.ecommerce.dto;

import java.util.List;

import lombok.Data;

@Data
public class ShelfAddRequestDTO {
	private String shopperId;
	private List<ShelfProductDTO> shelf;
	
}
