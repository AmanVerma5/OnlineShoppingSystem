package com.ecom.services;

import java.util.List;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.product.CategoryProductsDto;
import com.ecom.dtos.product.CategoryReqDto;
import com.ecom.dtos.product.CategoryRespDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public interface CategoryService {

	ApiResponse addNewCategory(CategoryReqDto dto);

	ApiResponse deleteCategory(Integer catId);

	List<CategoryRespDto> getAllCategories();

	CategoryRespDto getCategoryById(Integer catId);

	CategoryProductsDto getCategoryWithProducts(@Min(1) @Max(100) Integer catId);
	


}
