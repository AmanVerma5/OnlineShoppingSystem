package com.ecom.entities;

import com.ecom.enums.PaymentMethod;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Payment {

	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	private boolean paymentStatus;

	private String transactionId;

}
