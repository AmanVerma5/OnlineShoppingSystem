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
public class ProductResponseDto extends BaseDto {

	private String name;
	
	private double price;
	
	private int quantityInStock;
	
	private String description;

}
