package com.clone.ecommerece.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.clone.ecommerece.entity.Image;

public interface ImageRepo extends MongoRepository<Image, String>{

}
