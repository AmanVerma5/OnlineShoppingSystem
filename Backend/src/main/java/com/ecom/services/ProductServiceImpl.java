package com.ecom.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.ProductReqDto;
import com.ecom.dtos.ProductRespDto;
import com.ecom.entities.Product;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.repository.ProductDao;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductDao productDao;

	@Override
	public ApiResponse addProduct(ProductReqDto product,MultipartFile image) throws IOException {
		Product newProduct = modelMapper.map(product, Product.class);
		newProduct.setStatus(true);
		newProduct.setImage(image.getBytes());
		productDao.save(newProduct);
		return new ApiResponse("Added new product with ID " + newProduct.getId());
		
	}

	@Override
	public List<ProductRespDto> getAllProducts() {
		return productDao.findAll()
				.stream()
				.filter(product -> product.isStatus())
				.map(product -> modelMapper.map(product, ProductRespDto.class))
				.collect(Collectors.toList());
	}

//	@Override
//	public ApiResponse purchaseProduct(Integer id, int qty) {
//		Product product = productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Product Id"));
//		product.setQuantityInStock(product.getQuantityInStock() - qty);
//		return new ApiResponse("Purchased Product");
//	}
//
//	@Override
//	public ApiResponse deleteProduct(Integer id) {
//		Product product = productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Product Id"));
//		
//		if(product.isStatus())
//		{
//			product.setStatus(false);
//		}
//		return new ApiResponse("Product deleted successfully");
//	}
//
//	@Override
//	public ProductRespDto getCategoryAndProducts(Integer id) {
//		// INCOMPLETE
//		return null;
//	}


	

	
	
	
	

}
