package com.clone.ecommerece.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clone.ecommerece.repo.AuthRepo;
import com.clone.ecommerece.repo.CustomerRepo;
import com.clone.ecommerece.repo.SellerRepo;
import com.clone.ecommerece.requestDto.UsersRequest;
import com.clone.ecommerece.responseDto.UserResponse;
import com.clone.ecommerece.service.AuthService;
import com.clone.ecommerece.util.ResponseStructure;

@Service
public class AuthServiceImpl implements AuthService
{
	@Autowired
	private AuthRepo authRepo;
	
	@Autowired
	private SellerRepo sellerRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUsers(UsersRequest userRequest) 
	{
		return null;
		
	}

}
