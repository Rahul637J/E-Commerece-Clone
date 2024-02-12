package com.clone.ecommerece.service;

import org.springframework.http.ResponseEntity;

import com.clone.ecommerece.requestDto.AuthRequest;
import com.clone.ecommerece.requestDto.UsersRequest;
import com.clone.ecommerece.requestDto.otpModel;
import com.clone.ecommerece.responseDto.AuthResponse;
import com.clone.ecommerece.responseDto.UserResponse;
import com.clone.ecommerece.util.ResponseStructure;

import jakarta.servlet.http.HttpServletResponse;


public interface AuthService 
{
	
	ResponseEntity<ResponseStructure<UserResponse>> addUsers(UsersRequest userRequest);

	ResponseEntity<String> verifyOtp(otpModel otp);

	ResponseEntity<ResponseStructure<AuthResponse>> login(AuthRequest authRequest, HttpServletResponse httpServletResponse);

	ResponseEntity<ResponseStructure<String>> logout(String at, String rt, HttpServletResponse httpServletResponse);
}
