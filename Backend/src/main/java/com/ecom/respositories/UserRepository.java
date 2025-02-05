package com.ecom.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);

	User getUserByEmailAndPassword(String email, String password);

	User getUserByEmail(String email);
	
}
