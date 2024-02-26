package com.clone.ecommerece.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}
