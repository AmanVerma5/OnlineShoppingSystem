package com.ecom.dtos.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryReqDto {

	private String categoryName;

	
	public CategoryReqDto(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	
	
}
