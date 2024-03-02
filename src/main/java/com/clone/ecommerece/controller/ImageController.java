package com.clone.ecommerece.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clone.ecommerece.service.ImageService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/e-commerec/v1/api")
//This is called CORS Configuration (CROSS ORIGIN RESOURCE SHARE)
@CrossOrigin(allowCredentials = "true",origins = "http://localhost:5173/")
public class ImageController 
{
	private ImageService imageService;
	
	@PostMapping("/stores/{storeId}/images")
	public ResponseEntity<ResponseStructure<String>> addStoreImage(@PathVariable int storeId,MultipartFile image)
	{
		return imageService.addStoreImage(storeId,image);
	}
	
	@PostMapping("/products/{productId}/images")
	public ResponseEntity<ResponseStructure<String>> addProductImage(@PathVariable int productId,MultipartFile image)
	{
		return imageService.addProductImage(productId,image);
	}
	
	@PostMapping("/reviews/{reviewId}/images")
	public ResponseEntity<ResponseStructure<String>> addReviewImage(@PathVariable int reviewId,MultipartFile image)
	{
		return imageService.addReviewImage(reviewId,image);
	}
	
	@GetMapping("/stores/{imageId}/images")
	public ResponseEntity<byte[]> getStoreImage(@PathVariable String imageId)
	{
		return imageService.getStoreImage(imageId);
	}

}
