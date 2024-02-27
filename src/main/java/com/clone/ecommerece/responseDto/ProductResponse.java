package com.clone.ecommerece.responseDto;

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
public class ProductResponse {
	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	@Enumerated(EnumType.STRING)
	private AvailabilityStatus status;
	private double averageRating;
	private int totalOrders;
}
