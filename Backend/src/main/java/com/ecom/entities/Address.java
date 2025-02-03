package com.ecom.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String state;

	private String country;

	@Pattern(regexp = "^\\d{4,6}$")
	private String pincode;

}
