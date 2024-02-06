package com.clone.ecommerece.requestDto;

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
public class UsersRequest {
	private String email;
	private String password;
	private UserRole userRole;
}
