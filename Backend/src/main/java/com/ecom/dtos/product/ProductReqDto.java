package com.ecom.dtos.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductReqDto {
	
	private String name;
	private double price;
	private int quantityInStock;
	private int categoryId;
	private byte[] image;
	private String description;
	
	public ProductReqDto(String name, double price, int quantityInStock, String description) {
		this.name = name;
		this.price = price;
		this.quantityInStock = quantityInStock;
		this.description = description;
	}

	public ProductReqDto(String name, double price, int quantityInStock, String description, byte[] image) {
		super();
		this.name = name;
		this.price = price;
		this.quantityInStock = quantityInStock;
		this.description = description;
		this.image = image;
	}	
	
	
	
	
	

}
