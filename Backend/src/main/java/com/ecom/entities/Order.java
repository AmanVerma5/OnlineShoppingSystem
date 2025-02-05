package com.ecom.entities;

import java.util.List;

import com.ecom.enums.OrderStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order extends BaseEntity {

	@ManyToOne
	private User customer;

	private int numberOfItems;

	private double cartTotal;

	@ElementCollection
	private List<Item> items;

	@OneToOne
	private Address deliveryAddress;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Embedded
	private Payment paymentDetails;

}
