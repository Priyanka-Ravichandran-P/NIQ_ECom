package com.tredence.ecommerce.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Table(name = "products")
@Entity(name = "products")
public class Product{
	
	@Id
	@Column(name = "product_id")
	private String productId;
	private String category;
	private String brand;
}
