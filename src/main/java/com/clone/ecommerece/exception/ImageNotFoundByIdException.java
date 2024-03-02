package com.clone.ecommerece.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageNotFoundByIdException extends RuntimeException{
	private String message;

}
