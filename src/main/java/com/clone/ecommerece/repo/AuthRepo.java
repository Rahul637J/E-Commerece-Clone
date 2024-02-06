package com.clone.ecommerece.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.Users;

public interface AuthRepo extends JpaRepository<Users, Integer>
{
	
}
