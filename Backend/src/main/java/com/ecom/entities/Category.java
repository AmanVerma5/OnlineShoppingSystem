package com.ecom.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity {

	private String categoryName;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;

	// helper method to add products by cat
	public void addProducts(Product product) {
		this.products.add(product);
		product.setCategory(this);
	}

	// helper method to remove the product from cat
	public void removeProduct(Product product) {
		this.products.remove(product);
		product.setCategory(null);
	}

}
