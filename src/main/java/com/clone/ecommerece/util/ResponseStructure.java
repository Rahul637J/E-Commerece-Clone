package com.clone.ecommerece.util;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class ResponseStructure<T> {
	private int status;
	private String msg;
	private T data;
	

}
