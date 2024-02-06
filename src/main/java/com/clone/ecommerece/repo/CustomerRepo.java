package com.clone.ecommerece.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.Customers;

public interface CustomerRepo extends JpaRepository<Customers, Integer>{

}
