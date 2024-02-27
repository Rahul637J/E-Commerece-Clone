package com.clone.ecommerece.responseDto;

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
public class ContactResponse {
	private int contactId;
	private String contactName;
	private long contactNumber;
	private Priority priority;
}
