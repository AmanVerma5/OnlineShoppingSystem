package com.ecom.entities;

import java.time.LocalDate;
import java.util.List;

import com.ecom.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

	@Size(min = 2, max = 25, message = "First Name must be between 5 and 25 characters long")
	private String firstName;

	@Size(min = 2, max = 25, message = "Last Name must be between 5 and 25 characters long")
	private String lastName;

	@Email
	@Column(unique = true, nullable = false)
	private String email;

	private String password;

	@Size(min = 10, max = 10, message = "Mobile Number must be exactly 10 digits long")
	private String mobileNo;

	private LocalDate dateOfBirth;

	private boolean isActive;
	
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	@ElementCollection
	private List<Address> addresses;
	
	@OneToOne
	private Cart customerCart;
 
	@OneToMany(mappedBy = "vendor")
	private List<Product> vendorProducts;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> customerOrders;
	
}
