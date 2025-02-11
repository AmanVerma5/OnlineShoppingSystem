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
import com.ecom.dtos.product.ProductReqDto;
import com.ecom.dtos.product.ProductRespDto;
import com.ecom.dtos.product.ProductResponseDto;
import com.ecom.entities.Category;
import com.ecom.entities.Product;
import com.ecom.entities.User;
import com.ecom.exceptions.ApiException;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.repository.CategoryDao;
import com.ecom.repository.ProductDao;
import com.ecom.respositories.UserRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<ProductRespDto> getAllProducts() {
		return productDao.findAll().stream().filter(product -> product.isStatus())
				.map(product -> modelMapper.map(product, ProductRespDto.class)).collect(Collectors.toList());
	}

	@Override
	public ApiResponse addProduct(MultipartFile[] adsImages, String name, Double price, int quantityInStock,
			String description, Integer categoryId, String email) throws IOException {
		User persistentUser = userRepository.getUserByEmail(email);
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setQuantityInStock(quantityInStock);
		product.setDescription(description);
		byte[] arr = null;
		for (MultipartFile image : adsImages) {
			arr = image.getBytes();
		}
		product.setImage(arr);
		product.setStatus(true);
		Category persistentCategory = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Category Id"));
		persistentCategory.addProducts(product);
		persistentUser.addProducts(product);
		productDao.save(product);

		return new ApiResponse("Product added with id - " + product.getId());
	}

	//
	@Override
	public ApiResponse addProduct(ProductReqDto productDto, MultipartFile[] image, String email) {
		User persistentUser = userRepository.getUserByEmail(email);
//		Product product = new Product();
//		product.setName(name);
//		product.setPrice(price);
//		product.setQuantityInStock(quantityInStock);
//		product.setDescription(description);
//		
		Product product = modelMapper.map(productDto, Product.class);
		byte[] arr = null;
		for (MultipartFile i : image) {
			try {
				arr = i.getBytes();
			} catch (IOException e) {
				System.out.println(e);
				throw new ApiException(e.getMessage());
			}
		}
		product.setImage(arr);
		product.setStatus(true);
		Category persistentCategory = categoryDao.findById(productDto.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Category Id"));
		persistentCategory.addProducts(product);
		persistentUser.addProducts(product);
		productDao.save(product);
		return new ApiResponse("Product added with id - " + product.getId());
	}

	@Override
	public ApiResponse purchaseProduct(Integer id, int qty) {
		Product product = productDao.fetchProduct(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Product Id"));
		product.setQuantityInStock(product.getQuantityInStock() - qty);
		return new ApiResponse("Purchased Product");
	}

	@Override
	public ApiResponse deleteProduct(Integer id) {

		Product product = productDao.fetchProduct(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Product Id"));
		if (product.isStatus()) {
			product.setStatus(false);
		}

		return new ApiResponse("Product deleted Successfully");
	}

	@Override
	public List<ProductResponseDto> getAllProductsOfVendor(String email) {
		User persistentUser = userRepository.getUserByEmail(email);
		return persistentUser.getVendorProducts().stream().filter(product -> product.isStatus() != false)
				.map(products -> modelMapper.map(products, ProductResponseDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ProductRespDto> getAllProductsOfVendorWithImage(String email) {
		User persistentUser = userRepository.getUserByEmail(email);
		return persistentUser.getVendorProducts().stream()
				.map(products -> modelMapper.map(products, ProductRespDto.class)).collect(Collectors.toList());
	}

	@Override
	public ApiResponse updateVendorProduct(ProductResponseDto updatedProductDto) {
		Product persistentProduct = productDao.findById(updatedProductDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid productId"));
		persistentProduct.setName(updatedProductDto.getName());
		persistentProduct.setPrice(updatedProductDto.getPrice());
		persistentProduct.setQuantityInStock(updatedProductDto.getQuantityInStock());
		persistentProduct.setDescription(updatedProductDto.getDescription());
		
		return new ApiResponse("Product Details Updated");
	}

}
