package com.ecom.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.ProductReqDto;
import com.ecom.dtos.ProductRespDto;

public interface ProductService {

	ApiResponse addProduct(ProductReqDto newProduct,MultipartFile image) throws IOException;

	List<ProductRespDto> getAllProducts();

//	ApiResponse purchaseProduct(Integer id, int qty);
//
//	ApiResponse deleteProduct(Integer id);
//
//	ProductRespDto getCategoryAndProducts(Integer id);
	
	
	
	
	
	

}
