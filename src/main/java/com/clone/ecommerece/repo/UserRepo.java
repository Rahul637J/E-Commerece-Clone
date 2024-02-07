package com.clone.ecommerece.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>
{
	boolean existsByEmail(String email);

	Optional<User> findByUserName(String string);
}
