package com.ecom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.product.ProductRespDto;
import com.ecom.dtos.product.ProductResponseDto;
import com.ecom.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService prodService;

	@GetMapping("/view")
	public ResponseEntity<?> getAllProducts() {
		List<ProductRespDto> list = prodService.getAllProducts();

		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.ok(list);
		}
	}

	@GetMapping("/vendor_products")
	public ResponseEntity<?> getVendorProducts() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			List<ProductResponseDto> productsDto = prodService.getAllProductsOfVendor(authentication.getName());
			if (productsDto.size() == 0) {
				return ResponseEntity.ok().body(new ApiResponse("No Products Added"));
			} else {
				return ResponseEntity.ok(productsDto);
			}
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/vendor_products_with_image")
	public ResponseEntity<?> getVendorProductsWithImage() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			List<ProductRespDto> productsDto = prodService.getAllProductsOfVendorWithImage(authentication.getName());
			if (productsDto.size() == 0) {
				return ResponseEntity.ok().body(new ApiResponse("No Products Added"));
			} else {
				return ResponseEntity.ok(productsDto);
			}
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/purchase/{id}/{qty}")
	public ResponseEntity<?> purchaseProduct(@PathVariable Integer id, @PathVariable int qty) {
		return ResponseEntity.ok(prodService.purchaseProduct(id, qty));

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
		return ResponseEntity.ok(prodService.deleteProduct(id));
	}

	@PostMapping("/add_product")
	public ResponseEntity<?> addProduct(@RequestParam("myfile") MultipartFile[] adsImages,
			@RequestParam("name") String name, @RequestParam("price") Double price,
			@RequestParam("quantityInStock") int quantityInStock, @RequestParam("description") String description,
			@RequestParam Integer categoryId) throws IOException {

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			return ResponseEntity.status(HttpStatus.CREATED).body(prodService.addProduct(adsImages, name, price,
					quantityInStock, description, categoryId, authentication.getName()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

//	@PostMapping("/add_product")
//	public ResponseEntity<?> addProduct(@RequestBody ProductReqDto productDto, @RequestParam  MultipartFile[] image) throws IOException {
//
//		System.out.println(productDto);
//		try {
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			
//			return ResponseEntity.status(HttpStatus.CREATED)
//					.body(prodService.addProduct(productDto, image ,authentication.getName()));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//	}

	@PutMapping("/update_product")
	public ResponseEntity<?> updateVendorProduct(@RequestBody ProductResponseDto updatedProductDto) {
		try {
			return ResponseEntity.ok(prodService.updateVendorProduct(updatedProductDto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
