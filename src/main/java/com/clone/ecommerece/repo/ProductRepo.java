package com.clone.ecommerece.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
