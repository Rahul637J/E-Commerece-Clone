package com.clone.ecommerece.service;

import org.springframework.http.ResponseEntity;

import com.clone.ecommerece.requestDto.UsersRequest;
import com.clone.ecommerece.responseDto.UserResponse;
import com.clone.ecommerece.util.ResponseStructure;


public interface AuthService 
{
	
	ResponseEntity<ResponseStructure<UserResponse>> addUsers(UsersRequest userRequest);
}
