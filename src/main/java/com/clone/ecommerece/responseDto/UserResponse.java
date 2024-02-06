package com.clone.ecommerece.responseDto;

import com.clone.ecommerece.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private int usersId;
	private String userName;
	private String email;
	private UserRole userRole;
	private boolean isEmailVerified;
	private boolean isDeleted;
}
