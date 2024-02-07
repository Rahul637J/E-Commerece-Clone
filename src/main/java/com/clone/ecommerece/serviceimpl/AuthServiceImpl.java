package com.clone.ecommerece.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clone.ecommerece.entity.Customer;
import com.clone.ecommerece.entity.Seller;
import com.clone.ecommerece.entity.User;
import com.clone.ecommerece.exception.DuplicateRegisterException;
import com.clone.ecommerece.exception.UserNameAlreadyVerifiedEcxeption;
import com.clone.ecommerece.repo.CustomerRepo;
import com.clone.ecommerece.repo.SellerRepo;
import com.clone.ecommerece.repo.UserRepo;
import com.clone.ecommerece.requestDto.UsersRequest;
import com.clone.ecommerece.responseDto.UserResponse;
import com.clone.ecommerece.service.AuthService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
//@NoArgsConstructor
public class AuthServiceImpl implements AuthService
{
	private UserRepo userRepo;
	
	private SellerRepo sellerRepo;
	
	private PasswordEncoder passwordEncoder;
	
	private CustomerRepo customerRepo;
	
	private ResponseStructure<UserResponse> responseStructure;
	
	private <T extends User> T mapToPespectiveChild(UsersRequest userRequest) 
	{
		User user=null;
		System.out.println(userRequest.getUserRole());
	    switch(userRequest.getUserRole()) {
	    case SELLER ->{user =new Seller();}
	    case CUSTOMER ->{user =new Customer();}
	    }
	    user.setEmail(userRequest.getEmail());
	    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
	    user.setUserRole(userRequest.getUserRole());
		user.setUserName(userRequest.getEmail().split("@")[0]);
//		userRepo.save(user);
		return (T) user;
	}
	
	private UserResponse mapToResponse(User saveUser) 
	{
		return UserResponse.builder()
				.usersId(saveUser.getUserId())
				.userName(saveUser.getUserName())
				.email(saveUser.getEmail())
				.isEmailVerified(saveUser.isEmailVerified())
				.userRole(saveUser.getUserRole())
				.build();	
	}
	
	private <T extends User>T saveUser(UsersRequest usersRequest) 
	{
		User user=null;
		switch (usersRequest.getUserRole()) {
		case CUSTOMER->{user=customerRepo.save(mapToPespectiveChild(usersRequest));}
		case SELLER->{user=sellerRepo.save(mapToPespectiveChild(usersRequest));}
		}
		return (T) user;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUsers(UsersRequest userRequest) 
	{
		User user = userRepo.findByUserName(userRequest.getEmail().split("@")[0]).map(user1->{
			if(user1.isEmailVerified())
				throw new UserNameAlreadyVerifiedEcxeption("UserName is already verified with this email");
			else
				System.out.println();
			return user1;
		}).orElseGet(saveUser(userRequest));
		
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure
				  .setData(mapToResponse(user))
				  .setMsg("Plese verify the email using Otp which is sent to your email")
				  .setStatus(HttpStatus.ACCEPTED.value()),HttpStatus.ACCEPTED);	
	}
}	

