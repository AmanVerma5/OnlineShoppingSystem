package com.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.entities.Category;
import com.ecom.entities.Product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public interface ProductDao extends JpaRepository<Product, Integer> {
	
//	List<Product> findByCategory(Integer id);
	
	@Query("select p from Product p where p.id =:prodId")
	Optional<Product> fetchProduct(@Min(1) @Max(100) Integer prodId);

}
