package com.ecom.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Item {

	@OneToOne
	private Product product;
	
	private int quantity;
	
}
