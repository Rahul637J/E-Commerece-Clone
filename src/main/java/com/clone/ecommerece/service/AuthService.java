package com.clone.ecommerece.service;

import org.springframework.http.ResponseEntity;

import com.clone.ecommerece.requestDto.AuthRequest;
import com.clone.ecommerece.requestDto.OtpModel;
import com.clone.ecommerece.requestDto.UsersRequest;
import com.clone.ecommerece.responseDto.AuthResponse;
import com.clone.ecommerece.responseDto.UserResponse;
import com.clone.ecommerece.util.ResponseStructure;
import com.clone.ecommerece.util.SimpleReponse;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
	ResponseEntity<ResponseStructure<UserResponse>> addUsers(UsersRequest userRequest);

	ResponseEntity<String> verifyOtp(OtpModel otp);

	ResponseEntity<ResponseStructure<AuthResponse>> login(String at, String rt, AuthRequest authRequest,
			HttpServletResponse httpServletResponse);

	ResponseEntity<ResponseStructure<String>> logout(String at, String rt, HttpServletResponse httpServletResponse);

	ResponseEntity<SimpleReponse> revokeOther(String at, String rt, HttpServletResponse httpServletResponse);

	ResponseEntity<SimpleReponse> revokeOtherDeviceAccess(String accessToken, String refreshToken);

	ResponseEntity<ResponseStructure<AuthResponse>> refreshToken(String accessToken, String refreshToken,
			HttpServletResponse httpServletResponse);
}
