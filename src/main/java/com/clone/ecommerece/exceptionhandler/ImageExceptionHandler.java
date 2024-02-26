package com.clone.ecommerece.exceptionhandler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clone.ecommerece.exception.ImageNotFoundByIdException;

@RestControllerAdvice
public class ImageExceptionHandler {
	
	private ResponseEntity<Object> error(HttpStatus status,String message,Object rootCause)
	{
		return new ResponseEntity<Object>(Map.of(
				"status",status.value()
				,"Message",message
				,"RootCause",rootCause),status
				);
	}
	
	@ExceptionHandler(ImageNotFoundByIdException.class)
	public ResponseEntity<Object> imageNotFoundByIdException(ImageNotFoundByIdException exception)
	{
		return error(HttpStatus.BAD_REQUEST,"Invalid Contact ID",exception.getMessage());
	}

}
