package com.clone.ecommerece.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clone.ecommerece.requestDto.ProductRequest;
import com.clone.ecommerece.responseDto.ProductResponse;
import com.clone.ecommerece.service.ProductService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/e-commerec/v1/api")
//This is called CORS Configuration (CROSS ORIGIN RESOURCE SHARE)
@CrossOrigin(allowCredentials = "true",origins = "http://localhost:5173/")
public class ProductController 
{
	private ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<ProductResponse>> addProduct(@RequestBody ProductRequest productRequest)
	{
		return productService.addProduct(productRequest);
	}
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(@RequestBody ProductRequest productRequest,int productId)
	{
		return productService.updateProduct(productRequest,productId);
	}
	
}
