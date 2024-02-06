package com.clone.ecommerece.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clone.ecommerece.requestDto.UsersRequest;
import com.clone.ecommerece.responseDto.UserResponse;
import com.clone.ecommerece.service.AuthService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/ecc/v1")
public class AuthController 
{
	private AuthService authService;
	
	@PostMapping("/addusers")
	public  ResponseEntity<ResponseStructure<UserResponse>> addUsers(@RequestBody UsersRequest userRequest)
	{
		System.out.println(userRequest.getEmail()+userRequest.getUserRole());
		 return authService.addUsers(userRequest);
	}
}
