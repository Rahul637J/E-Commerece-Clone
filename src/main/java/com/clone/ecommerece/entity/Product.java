package com.clone.ecommerece.entity;

import com.clone.ecommerece.enums.AvailabilityStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	@Enumerated(EnumType.STRING)
	private AvailabilityStatus status;
	private double averageRating;
	private int totalOrders;
	
	@ManyToOne
	private Store store;
	
}
