package com.ecom.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_addresses")
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String state;

	private String country;

	@Pattern(regexp = "^\\d{4,6}$")
	private String pincode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

}
