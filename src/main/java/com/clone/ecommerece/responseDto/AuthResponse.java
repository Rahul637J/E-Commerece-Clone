package com.clone.ecommerece.responseDto;

import java.time.LocalDateTime;

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
public class AuthResponse 
{
	private int userId;
	private String username;
	private String role;
	private boolean isAuthenticated;
	private LocalDateTime accessExpiratioin;
	private LocalDateTime refreshExpiration;

}
