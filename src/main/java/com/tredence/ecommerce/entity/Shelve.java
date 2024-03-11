package com.tredence.ecommerce.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "shelves")
@Table(name = "shelves")
@IdClass(Shelf.class)
public class Shelve {

	@Id
	@Column(name = "shopper_id")
	private String shopperId;

	@Id
	@Column(name = "product_id")
	private String productId;

	@Column(name = "relevancy_score")
	private double relevancyScore;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product products;

	public Shelve(String shopperId, String productId, Double relevancyScore) {
		this.shopperId = shopperId;
		this.productId = productId;
		this.relevancyScore = relevancyScore;
	}

	public Shelve() {
	}

}
