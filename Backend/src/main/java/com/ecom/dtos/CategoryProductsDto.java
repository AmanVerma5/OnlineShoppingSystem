package com.ecom.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class CategoryProductsDto extends BaseDto {
	
	private String categoryName;
	private List<ProductRespDto> products;
	
	

}
