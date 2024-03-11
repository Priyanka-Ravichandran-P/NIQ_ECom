package com.tredence.ecommerce.service;

import java.util.List;

import com.tredence.ecommerce.dto.ProductRequestDTO;
import com.tredence.ecommerce.dto.ShelfAddRequestDTO;
import com.tredence.ecommerce.dto.ShopperResponseDTO;
import com.tredence.ecommerce.entity.Product;

public interface ShelveService {
	List<ShopperResponseDTO> getShoppersByProductId(String productId, int limit);
	void addShelve(List<ShelfAddRequestDTO> shelves);
}
