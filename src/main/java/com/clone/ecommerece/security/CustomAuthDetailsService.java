package com.clone.ecommerece.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.clone.ecommerece.repo.UserRepo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthDetailsService implements UserDetailsService{

	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUserName(username).map(user -> new CustomAuthDetails(user))
				.orElseThrow(() -> new UsernameNotFoundException("User name found in the given Database register"));
	}

}
