package com.clone.ecommerece.util;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.clone.ecommerece.entity.AccessToken;
import com.clone.ecommerece.entity.RefreshToken;
import com.clone.ecommerece.entity.User;
import com.clone.ecommerece.repo.AccessTokenRepo;
import com.clone.ecommerece.repo.RefreshTokenRepo;
import com.clone.ecommerece.repo.UserRepo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ScheduledJobs 
{
	
	private UserRepo userRepo;
	private AccessTokenRepo accessTokenRepo;
	private RefreshTokenRepo refreshTokenRepo;
	
	@Scheduled(fixedDelay = 1000l*60*5)
	public void cleanUpNonVerifiedUsers()
	{
		List<User> user=userRepo.getAllByIsEmailVerified(false);
		userRepo.deleteAll(user);
	}
	
	@Scheduled(fixedDelay = 1000l*60*5)
	public void cleanUpExpiredTokens()
	{
		List<AccessToken> accessTokens = accessTokenRepo.findByExpirationBefore(LocalDateTime.now());
//		for(AccessToken accessToken:accessTokens)
//		System.out.println(accessToken.getTokenId());
		accessTokenRepo.deleteAll(accessTokens);
		
		List<RefreshToken> refreshTokens = refreshTokenRepo.findByExpirationBefore(LocalDateTime.now());
		refreshTokenRepo.deleteAll(refreshTokens);
	}
	
}
