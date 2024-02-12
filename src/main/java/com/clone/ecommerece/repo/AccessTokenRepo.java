package com.clone.ecommerece.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.AccessToken;

import jakarta.servlet.http.Cookie;

public interface AccessTokenRepo extends JpaRepository<AccessToken, Long>{

	Optional<AccessToken> findByToken(String at);

}
