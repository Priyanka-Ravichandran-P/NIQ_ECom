package com.tredence.ecommerce.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tredence.ecommerce.dto.ProductRequestDTO;
import com.tredence.ecommerce.dto.ProductResponseDTO;
import com.tredence.ecommerce.entity.Product;
import com.tredence.ecommerce.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping
	public ResponseEntity<ProductResponseDTO> addProducts(@RequestBody List<Product> productList) {
		
		productService.saveProducts(productList);
		ProductResponseDTO productResponseDTO = new ProductResponseDTO("Created Products");
		ResponseEntity<ProductResponseDTO> responseEntity = ResponseEntity.status(HttpStatus.CREATED)
																.body(productResponseDTO);
		return responseEntity;

	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductsByProductId(
			@Validated @PathVariable("productId") @NotNull(message = "Product Id Required") String productId) {
		Product product = productService.getProductByProductId(productId);
		ResponseEntity<Product> responseEntity = ResponseEntity.ok(product);
		return responseEntity;
	}

	@GetMapping(path = "/shopperId/{shopperId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<Product>> getProductByShopperId(
			@PathVariable @NotNull(message = "Shopper Id Required") String shopperId,
			@RequestBody ProductRequestDTO requestDTO) {

		List<Product> products = productService.getProductsByShopper(shopperId, requestDTO);
		ResponseEntity<List<Product>> responseEntity = ResponseEntity.ok(products);
		return responseEntity;
	}

}
