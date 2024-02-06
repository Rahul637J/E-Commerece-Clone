package com.clone.ecommerece.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.Sellers;

public interface SellerRepo extends JpaRepository<Sellers, Integer>{
	

}
