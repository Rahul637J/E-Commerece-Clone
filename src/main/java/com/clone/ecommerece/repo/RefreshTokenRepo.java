package com.clone.ecommerece.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.RefreshToken;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long>{

}
