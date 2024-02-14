package com.clone.ecommerece.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;

import com.clone.ecommerece.requestDto.AuthRequest;
import com.clone.ecommerece.requestDto.UsersRequest;
import com.clone.ecommerece.requestDto.otpModel;
import com.clone.ecommerece.responseDto.AuthResponse;
import com.clone.ecommerece.responseDto.UserResponse;
import com.clone.ecommerece.util.SimpleReponse;
import com.clone.ecommerece.util.ResponseStructure;

import jakarta.servlet.http.HttpServletResponse;


public interface AuthService 
{
	
	ResponseEntity<ResponseStructure<UserResponse>> addUsers(UsersRequest userRequest);

	ResponseEntity<String> verifyOtp(otpModel otp);

	ResponseEntity<ResponseStructure<AuthResponse>> login(@CookieValue(value = "at",required = false) String at,
			@CookieValue (value = "rt",required = false)String rt,AuthRequest authRequest, HttpServletResponse httpServletResponse);

	ResponseEntity<ResponseStructure<String>> logout(String at, String rt, HttpServletResponse httpServletResponse);

	ResponseEntity<SimpleReponse> revokeOther(String at, String rt,
			HttpServletResponse httpServletResponse);

	ResponseEntity<SimpleReponse> revokeOtherDeviceAccess(String accessToken, String refreshToken);

	ResponseEntity<SimpleReponse> refreshToken(String accessToken, String refreshToken,
			HttpServletResponse httpServletResponse);
}
