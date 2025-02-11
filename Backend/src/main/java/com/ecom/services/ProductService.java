package com.ecom.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.product.ProductReqDto;
import com.ecom.dtos.product.ProductRespDto;
import com.ecom.dtos.product.ProductResponseDto;

public interface ProductService {


	ApiResponse addProduct(MultipartFile[] adsImages, String name, Double price, int quantityInStock, String description, Integer categoryId, String email) throws IOException;

	List<ProductRespDto> getAllProducts();

	ApiResponse purchaseProduct(Integer id, int qty);
	
	ApiResponse deleteProduct(Integer id);

	ApiResponse addProduct(ProductReqDto productDto, MultipartFile[] image, String email);

	List<ProductResponseDto> getAllProductsOfVendor(String name);

	List<ProductRespDto> getAllProductsOfVendorWithImage(String name);

	ApiResponse updateVendorProduct(ProductResponseDto updatedProductDto);
	
	
	
	

}
