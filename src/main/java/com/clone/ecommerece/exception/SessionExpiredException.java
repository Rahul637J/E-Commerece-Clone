package com.clone.ecommerece.exception;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class SessionExpiredException extends RuntimeException {
	private String message;

}
