package com.clone.ecommerece.service;

import org.springframework.http.ResponseEntity;

import com.clone.ecommerece.requestDto.ProductRequest;
import com.clone.ecommerece.responseDto.ProductResponse;
import com.clone.ecommerece.util.ResponseStructure;

public interface ProductService {

	ResponseEntity<ResponseStructure<ProductResponse>> addProduct(ProductRequest productRequest);

	ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(ProductRequest productRequest, int productId);

}
