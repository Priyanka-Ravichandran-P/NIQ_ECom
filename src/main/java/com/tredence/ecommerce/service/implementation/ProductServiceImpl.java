package com.tredence.ecommerce.service.implementation;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tredence.ecommerce.dto.ProductRequestDTO;
import com.tredence.ecommerce.entity.Product;
import com.tredence.ecommerce.exception.ProductNotFoundException;
import com.tredence.ecommerce.repository.ProductRepository;
import com.tredence.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public void saveProducts(List<Product> products) {
	productRepository.saveAll(products);
	}
	
	@Override
	public Product getProductByProductId(String productId) {
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) return product.get();
        throw new ProductNotFoundException("Product for product Id" + productId+ " not found");
	}

	@Override
	public List<Product> getProductsByShopper(String shopperId, ProductRequestDTO productReqDTO) {
		String category = productReqDTO.getCategory();
		String brand = productReqDTO.getBrand();
		Pageable pageRequest = PageRequest.of(0, productReqDTO.getLimit());
		List<Product> products = productRepository.getProductsByShopper(shopperId, category, brand, pageRequest);
		if(products.isEmpty()) throw new ProductNotFoundException("Products for shopper Id" + shopperId+ " not found");

		return products;
	}
	
}
