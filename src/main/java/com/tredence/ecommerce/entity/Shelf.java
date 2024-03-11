package com.tredence.ecommerce.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Shelf implements Serializable {

	private static final long serialVersionUID = 1L;
	private String shopperId;
    private String productId;

    // Override equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelfId = (Shelf) o;
        return Objects.equals(shopperId, shelfId.shopperId) &&
                Objects.equals(productId, shelfId.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopperId, productId);
    }
}