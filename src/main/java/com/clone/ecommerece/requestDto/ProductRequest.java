package com.clone.ecommerece.requestDto;

import com.clone.ecommerece.enums.AvailabilityStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ProductRequest {
	private String productName;
	private String productDescription;
	private double productPrice;
	@Enumerated(EnumType.STRING)
	private AvailabilityStatus status;
	private double averageRating;
	private int totalOrders;
}
