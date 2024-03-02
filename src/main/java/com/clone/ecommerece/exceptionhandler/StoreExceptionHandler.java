package com.clone.ecommerece.exceptionhandler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clone.ecommerece.exception.SellerNotHavingStoreException;
import com.clone.ecommerece.exception.StoreAlreadyHasAddressException;
import com.clone.ecommerece.exception.StoreNotFoundByIdException;

@RestControllerAdvice
public class StoreExceptionHandler {
	private ResponseEntity<Object> error(HttpStatus status,String message,Object rootCause)
	{
		return new ResponseEntity<Object>(Map.of(
				"status",status.value()
				,"Message",message
				,"RootCause",rootCause),status
				);
	}
	
	@ExceptionHandler(StoreNotFoundByIdException.class)
	public ResponseEntity<Object> storeNotFoundByIdException(StoreNotFoundByIdException exception)
	{
		return error(HttpStatus.BAD_REQUEST,"Store Not Found",exception.getMessage());
	}
	
	@ExceptionHandler(StoreAlreadyHasAddressException.class)
	public ResponseEntity<Object> storeAlreadyHasAddressException(StoreAlreadyHasAddressException exception)
	{
		return error(HttpStatus.BAD_REQUEST,"Address already exist",exception.getMessage());
	}
	
	@ExceptionHandler(SellerNotHavingStoreException.class)
	public ResponseEntity<Object> sellerNotHavingStoreException(SellerNotHavingStoreException exception)
	{
		return error(HttpStatus.BAD_REQUEST,"Store Not Exist in Seller",exception.getMessage());
	}
}
