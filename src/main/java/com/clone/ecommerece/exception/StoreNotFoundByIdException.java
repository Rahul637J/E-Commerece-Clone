package com.clone.ecommerece.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreNotFoundByIdException extends RuntimeException{
	private String message;
}
