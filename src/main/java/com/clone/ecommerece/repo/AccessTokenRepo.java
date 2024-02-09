package com.clone.ecommerece.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.AccessToken;

public interface AccessTokenRepo extends JpaRepository<AccessToken, Long>{

}
