package com.clone.ecommerece.requestDto;

import com.clone.ecommerece.enums.Priority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ContactRequest {
	private String contactName; 
	private long contactNumber;
	private Priority priority;
}
