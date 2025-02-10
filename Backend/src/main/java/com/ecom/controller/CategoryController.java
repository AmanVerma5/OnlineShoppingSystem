package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.product.CategoryReqDto;
import com.ecom.dtos.product.CategoryRespDto;
import com.ecom.services.CategoryService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<?> addNewCategory(@RequestBody CategoryReqDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addNewCategory(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
	}

	@DeleteMapping("/{catId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer catId) {

		return ResponseEntity.ok(categoryService.deleteCategory(catId));

	}

	@GetMapping("/view")
	public ResponseEntity<?> getAllCategories() {
		List<CategoryRespDto> list = categoryService.getAllCategories();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.ok(list);
		}
	}

	@GetMapping("/view/{catId}")
	public ResponseEntity<?> getCategoryById(@PathVariable @Min(1) @Max(100) Integer catId) {
		return ResponseEntity.ok(categoryService.getCategoryById(catId));
	}

	// get Category and their respective products
	@GetMapping("/{catId}/products")
	public ResponseEntity<?> getCategoryWithProducts(@PathVariable @Min(1) @Max(100) Integer catId) {
		try {
			return ResponseEntity.ok(categoryService.getCategoryWithProducts(catId));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse(e.getMessage()));
		}
	}

}
