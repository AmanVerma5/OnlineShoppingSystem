package com.ecom.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity {

	@ManyToOne
	private User vendor;

	private String name;
	
	private String description;
	
	private double price;
	
	private int quantityInStock;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@Lob
    private byte[] image;

}
