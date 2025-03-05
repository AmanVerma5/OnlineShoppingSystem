package com.ecom.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.product.CategoryProductsDto;
import com.ecom.dtos.product.CategoryReqDto;
import com.ecom.dtos.product.CategoryRespDto;
import com.ecom.entities.Category;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.repository.CategoryDao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse addNewCategory(CategoryReqDto dto) {
		
		Category transcategory = modelMapper.map(dto, Category.class);
		
		Category persistCat = categoryDao.save(transcategory);
		
		return new ApiResponse("Category Added with id - " + persistCat.getId());
	}

	@Override
	public ApiResponse deleteCategory(Integer catId) {
		String msg = "Invalid Category id";
		if(categoryDao.existsById(catId))
		{
			categoryDao.deleteById(catId);
			msg = "Category Deleted Successfully";
		}
		return new ApiResponse(msg);
	}

	@Override
	public List<CategoryRespDto> getAllCategories() {
		
		return categoryDao.findAll()
				.stream()
				.map(category -> modelMapper.map(category, CategoryRespDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryRespDto getCategoryById(Integer catId) {
		
		Category category = categoryDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Invalid Category Id"));
		
		return modelMapper.map(category, CategoryRespDto.class);
	}

	@Override
	public CategoryProductsDto getCategoryWithProducts(@Min(1) @Max(100) Integer catId) {
		
		Category category = categoryDao.getCategoryWithProducts(catId)
				.orElseThrow(()
						-> new ResourceNotFoundException("Invalid Category Id"));
		
		return modelMapper.map(category, CategoryProductsDto.class);
	}

	@Override
	public ApiResponse updateCategory(Integer catId, CategoryReqDto dto) {
		Category persistentCat = categoryDao.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Category Id"));
		persistentCat.setCategoryName(dto.getCategoryName());
		return new ApiResponse("Category Updated");
	}

}
