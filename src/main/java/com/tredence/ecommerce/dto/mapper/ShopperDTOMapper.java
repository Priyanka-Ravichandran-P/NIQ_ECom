package com.tredence.ecommerce.dto.mapper;

import org.springframework.stereotype.Component;

import com.tredence.ecommerce.dto.ShopperResponseDTO;
import com.tredence.ecommerce.entity.Shelve;

import lombok.Data;

@Data
@Component
public class ShopperDTOMapper {

	public ShopperResponseDTO mapToShopperResponseDTO(Shelve shelfObj) {
		ShopperResponseDTO shopperDTO = new ShopperResponseDTO();
		shopperDTO.setShopperId(shelfObj.getShopperId());
		shopperDTO.setRelevancyScore(shelfObj.getRelevancyScore());
		return shopperDTO;
	}
}
