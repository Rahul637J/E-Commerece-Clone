package com.clone.ecommerece.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
