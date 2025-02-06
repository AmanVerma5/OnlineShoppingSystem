package com.ecom.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class CategoryRespDto extends BaseDto {
	private String categoryName;
	
	
	

}
