package com.clone.ecommerece.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>
{
	boolean existsByEmail(String email);
}
