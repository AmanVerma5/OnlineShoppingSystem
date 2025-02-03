package com.ecom.entities;

import java.time.LocalDate;

import com.ecom.enums.CardType;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Card {

	private String cardNo;

	@Size(min = 100, max = 999, message = "CVV must be exactly 3 digits")
	private int cvv;

	private LocalDate cardExpiryDate;

	@Enumerated(EnumType.STRING)
	private CardType cardType;

}
