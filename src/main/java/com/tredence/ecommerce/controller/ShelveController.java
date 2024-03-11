package com.tredence.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tredence.ecommerce.dto.ShelfAddRequestDTO;
import com.tredence.ecommerce.dto.ShelfResponseEntityDTO;
import com.tredence.ecommerce.dto.ShopperResponseDTO;
import com.tredence.ecommerce.service.ShelveService;

@Controller
@RequestMapping(value = "/shelve")
public class ShelveController {

	@Autowired
	ShelveService shelveService;

	@GetMapping("/shopper/{productId}")
	public ResponseEntity<List<ShopperResponseDTO>> getShoppers(@PathVariable String productId,
			@RequestParam(defaultValue = "10") int limit) {
		List<ShopperResponseDTO> shoppersList = shelveService.getShoppersByProductId(productId, limit);
		ResponseEntity<List<ShopperResponseDTO>> responseEntity = ResponseEntity.ok(shoppersList);
		return responseEntity;
	}

	@PostMapping
	public ResponseEntity<ShelfResponseEntityDTO> addShelves(@RequestBody List<ShelfAddRequestDTO> shelves) {
		shelveService.addShelve(shelves);
		ShelfResponseEntityDTO shelfRepositoryDTO = new ShelfResponseEntityDTO("Shelves Created Succesfully");
		ResponseEntity<ShelfResponseEntityDTO> responseEntity = ResponseEntity.status(HttpStatus.CREATED)
				.body(shelfRepositoryDTO);
		return responseEntity;
	}

}
