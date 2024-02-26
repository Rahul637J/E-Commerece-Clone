package com.clone.ecommerece.service;

import org.springframework.http.ResponseEntity;

import com.clone.ecommerece.requestDto.StoreRequest;
import com.clone.ecommerece.responseDto.StoreResponse;
import com.clone.ecommerece.util.ResponseStructure;

public interface StoreService {

	ResponseEntity<ResponseStructure<StoreResponse>> addStore(StoreRequest request,int sellerId);

	ResponseEntity<ResponseStructure<StoreResponse>> updateStore(StoreRequest request,int storeId);

	ResponseEntity<ResponseStructure<StoreResponse>> findStoreById(int storeId);

	ResponseEntity<ResponseStructure<StoreResponse>> findStoreBySeller(int sellerId);

}
