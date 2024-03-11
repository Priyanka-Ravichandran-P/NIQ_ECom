package com.tredence.ecommerce.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tredence.ecommerce.dto.ShelfAddRequestDTO;
import com.tredence.ecommerce.dto.ShelfProductDTO;
import com.tredence.ecommerce.dto.ShopperResponseDTO;
import com.tredence.ecommerce.dto.mapper.ShopperDTOMapper;
import com.tredence.ecommerce.entity.Shelve;
import com.tredence.ecommerce.exception.ShopperNotFoundException;
import com.tredence.ecommerce.repository.ProductRepository;
import com.tredence.ecommerce.repository.ShelveRepository;
import com.tredence.ecommerce.service.ShelveService;

@Service
public class ShelveServiceImpl implements ShelveService {
	@Autowired
	ShelveRepository shelveRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ShopperDTOMapper shopperDTOMapper;

	public List<ShopperResponseDTO> getShoppersByProductId(String productId, int limit) {
		Pageable pageRequest = PageRequest.of(0, limit);
		List<Shelve> listOfShelf = shelveRepository.getShoppersByProductId(productId, pageRequest);
		List<ShopperResponseDTO> listOfShopper = new ArrayList<ShopperResponseDTO>();
		listOfShelf.forEach(shelve -> listOfShopper.add(shopperDTOMapper.mapToShopperResponseDTO(shelve)));
		if(listOfShopper.isEmpty()) throw new ShopperNotFoundException("Shopper for product id" + productId+ " not found");
		return listOfShopper;
	}

	@Override
	public void addShelve(List<ShelfAddRequestDTO> listOfShelves) {

		List<String> listOfProductIds = productRepository.getProductIds();

		List<String> shelfProductIds = listOfShelves.stream().flatMap(shelveObj -> shelveObj.getShelf().stream())
				.map(ShelfProductDTO::getProductId).collect(Collectors.toList());

		List<String> listOfUnAvailableProductIds = shelfProductIds.stream().filter(p -> !listOfProductIds.contains(p))
				.collect(Collectors.toList());

		List<ShelfAddRequestDTO> shelveDataWithAvailableProducts = removeProductsFromShelf(listOfShelves,
				listOfUnAvailableProductIds);

		List<Shelve> shopperShelfList = shelveDataWithAvailableProducts.stream().flatMap(shopperObj -> shopperObj
				.getShelf().stream()
				.map(shelf -> new Shelve(shopperObj.getShopperId(), shelf.getProductId(), shelf.getRelevancyScore())))
				.collect(Collectors.toList());

		shelveRepository.saveAll(shopperShelfList);
	}

	List<ShelfAddRequestDTO> removeProductsFromShelf(List<ShelfAddRequestDTO> shelvesDTOList,
			List<String> listOfUnAvailableProductIds) {

		return shelvesDTOList.stream().map(shelfDto -> {
			List<ShelfProductDTO> updatedShelf = shelfDto.getShelf().stream()
					.filter(item -> !listOfUnAvailableProductIds.contains(item.getProductId()))
					.collect(Collectors.toList());
			shelfDto.setShelf(updatedShelf);
			return shelfDto;
		}).collect(Collectors.toList());

	}
}
