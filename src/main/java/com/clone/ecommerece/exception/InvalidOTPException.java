package com.clone.ecommerece.exception;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class InvalidOTPException extends RuntimeException {
	private String message;

}
