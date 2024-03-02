package com.clone.ecommerece.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.Store;

public interface StoreRepo extends JpaRepository<Store, Integer>
{

}
