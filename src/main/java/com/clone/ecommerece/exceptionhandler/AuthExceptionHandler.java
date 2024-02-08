package com.clone.ecommerece.exceptionhandler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clone.ecommerece.exception.DuplicateRegisterException;
import com.clone.ecommerece.exception.InvalidOTPException;
import com.clone.ecommerece.exception.OtpExpiredException;
import com.clone.ecommerece.exception.SessionExpiredException;
import com.clone.ecommerece.exception.UserNameAlreadyVerifiedEcxeption;

@RestControllerAdvice
public class AuthExceptionHandler 
{
	private ResponseEntity<Object> error(HttpStatus status,String message,Object rootCause)
	{
		return new ResponseEntity<Object>(Map.of(
				"status",status.value()
				,"Message",message
				,"RootCause",rootCause),status
				);
	}
	
	@ExceptionHandler(DuplicateRegisterException.class)
	public ResponseEntity<Object> duplicateRegisterException(DuplicateRegisterException exception)
	{
		return error(HttpStatus.BAD_REQUEST,"UserEmail already exist",exception.getMessage());
	}
	
	@ExceptionHandler(UserNameAlreadyVerifiedEcxeption.class)
	public ResponseEntity<Object> userNameAlreadyVerifiedException(UserNameAlreadyVerifiedEcxeption exception)
	{
		return error(HttpStatus.BAD_REQUEST,"UserEmail already exist",exception.getMessage());
	}
	
	@ExceptionHandler(SessionExpiredException.class)
	public ResponseEntity<Object> SessionExpiredExceptionException(SessionExpiredException exception)
	{
		return error(HttpStatus.BAD_REQUEST,"Session Expired Re-register",exception.getMessage());
	}
	
	@ExceptionHandler(OtpExpiredException.class)
	public ResponseEntity<Object> OtpExpiredException(OtpExpiredException exception)
	{
		return error(HttpStatus.BAD_REQUEST,"OTP Expired Re-send OTP",exception.getMessage());
	}
	
	@ExceptionHandler(InvalidOTPException.class)
	public ResponseEntity<Object> invalidOTPException(InvalidOTPException exception)
	{
		return error(HttpStatus.BAD_REQUEST,"OTP Expired Re-send OTP",exception.getMessage());
	}
	

}
