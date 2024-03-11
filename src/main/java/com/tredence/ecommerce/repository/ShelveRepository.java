package com.tredence.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tredence.ecommerce.entity.Shelf;
import com.tredence.ecommerce.entity.Shelve;

@Repository
public interface ShelveRepository extends JpaRepository<Shelve, Shelf> {

	@Query("SELECT s FROM shelves s JOIN products p ON s.productId = :productId ORDER BY relevancyScore DESC")
	public List<Shelve> getShoppersByProductId(@Param("productId") String productId, Pageable pageable);
			

}
