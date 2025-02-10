package com.ecom.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.product.ProductReqDto;
import com.ecom.dtos.product.ProductRespDto;

public interface ProductService {


	ApiResponse addProduct(MultipartFile[] adsImages, String name, Double price, int quantityInStock, String description, Integer catId) throws IOException;

	List<ProductRespDto> getAllProducts();

	ApiResponse purchaseProduct(Integer id, int qty);
	
	ApiResponse deleteProduct(Integer id);

	
	
	
	
	
	

}
