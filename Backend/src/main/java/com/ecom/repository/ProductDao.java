package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
	
//	List<Product> findByCategory(Integer id);

}
