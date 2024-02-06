package com.clone.ecommerece.requestDto;

import com.clone.ecommerece.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class UsersRequest {
	private String email;
	private String password;
	private UserRole userRole;
	private boolean isEmailVerified;
	private boolean isDeleted;
}
