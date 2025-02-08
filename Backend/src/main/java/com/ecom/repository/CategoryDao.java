package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.entities.Category;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public interface CategoryDao extends JpaRepository<Category, Integer> {

	@Query("select c from Category c join fetch c.products where c.id =:catId")
	Optional<Category> getCategoryWithProducts(@Min(1) @Max(100) Integer catId);

}
