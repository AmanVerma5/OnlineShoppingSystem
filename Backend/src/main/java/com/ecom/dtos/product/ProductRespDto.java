package com.ecom.dtos.product;

import com.ecom.dtos.BaseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ProductRespDto extends BaseDto {

	private String name;
	private double price;
	private int quantityInStock;
	private String description;
	private byte[] image;
	
	public ProductRespDto(String name, double price, int quantityInStock, String description, byte[] image) {
		this.name = name;
		this.price = price;
		this.quantityInStock = quantityInStock;
		this.description = description;
		this.image = image;
	}
	
	
}
