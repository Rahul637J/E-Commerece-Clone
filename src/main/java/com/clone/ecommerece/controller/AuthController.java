package com.clone.ecommerece.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clone.ecommerece.entity.AccessToken;
import com.clone.ecommerece.entity.RefreshToken;
import com.clone.ecommerece.requestDto.AuthRequest;
import com.clone.ecommerece.requestDto.UsersRequest;
import com.clone.ecommerece.requestDto.otpModel;
import com.clone.ecommerece.responseDto.AuthResponse;
import com.clone.ecommerece.responseDto.UserResponse;
import com.clone.ecommerece.service.AuthService;
import com.clone.ecommerece.util.ResponseStructure;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/e-commerec/v1/api")
public class AuthController 
{
	private AuthService authService;
	
	@PostMapping("/users")
	public  ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody UsersRequest userRequest)
	{
		 return authService.addUsers(userRequest);
	}
	
	@PostMapping("/users/verifyotp")
	public ResponseEntity<String> verifyOtp(@RequestBody otpModel otp)
	{
		return authService.verifyOtp(otp);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<AuthResponse>> login(@RequestBody AuthRequest authRequest,HttpServletResponse httpServletResponse)
	{
		return authService.login(authRequest,httpServletResponse);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<ResponseStructure<String>>logout(@CookieValue(value = "at",required = false) String at,
			@CookieValue (value = "rt",required = false)String rt,HttpServletResponse httpServletResponse)
	{
		return authService.logout(at,rt,httpServletResponse);
	}
}
