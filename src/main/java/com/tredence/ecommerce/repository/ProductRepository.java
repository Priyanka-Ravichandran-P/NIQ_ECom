package com.tredence.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tredence.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	@Query("SELECT p FROM products p JOIN shelves s ON p.productId = s.productId WHERE s.shopperId = :shopperId "
			+ "AND (:category IS NULL OR p.category = :category) " + "AND (:brand IS NULL OR p.brand = :brand) "
			+ "ORDER BY s.relevancyScore DESC")
	List<Product> getProductsByShopper(@Param("shopperId") String shopperId, @Param("category") String category,
			@Param("brand") String brand, Pageable pageable);
	
	@Query("SELECT DISTINCT p.productId FROM products p")
    List<String> getProductIds();

}
