package com.clone.ecommerece.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CookiesNotCreatedException extends RuntimeException {
	private String message;
}
