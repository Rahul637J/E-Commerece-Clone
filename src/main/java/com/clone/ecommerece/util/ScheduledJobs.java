package com.clone.ecommerece.util;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.clone.ecommerece.entity.User;
import com.clone.ecommerece.repo.UserRepo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ScheduledJobs {
	
	private UserRepo userRepo;
	
	@Scheduled(fixedDelay = 1000l*60*5)
	public void cleanUpNonVerifiedUsers()
	{
		List<User> user=userRepo.getAllByIsEmailVerified(false);
		userRepo.deleteAll(user);
	}
}
