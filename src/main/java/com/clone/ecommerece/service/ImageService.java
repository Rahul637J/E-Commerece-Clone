package com.clone.ecommerece.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.clone.ecommerece.util.ResponseStructure;

public interface ImageService {

	ResponseEntity<ResponseStructure<String>> addStoreImage(int storeId, MultipartFile image);

	ResponseEntity<ResponseStructure<String>> addProductImage(int productId, MultipartFile image);

	ResponseEntity<ResponseStructure<String>> addReviewImage(int reviewId, MultipartFile image);

	ResponseEntity<byte []> getStoreImage(String imageId);

}
