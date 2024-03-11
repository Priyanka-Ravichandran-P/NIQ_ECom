package com.tredence.ecommerce.service;

import java.util.List;

import com.tredence.ecommerce.dto.ProductRequestDTO;
import com.tredence.ecommerce.entity.Product;


public interface ProductService {
	
	 void saveProducts(List<Product> products);
	 Product getProductByProductId(String productId);
	 List<Product> getProductsByShopper(String shopperId, ProductRequestDTO productReqDTO);
	 
}
