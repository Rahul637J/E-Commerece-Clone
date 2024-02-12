package com.clone.ecommerece.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.RefreshToken;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long>{

	Optional<RefreshToken> findByToken(String rt);

}
