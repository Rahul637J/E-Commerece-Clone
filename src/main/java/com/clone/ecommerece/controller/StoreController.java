package com.clone.ecommerece.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clone.ecommerece.requestDto.StoreRequest;
import com.clone.ecommerece.responseDto.StoreResponse;
import com.clone.ecommerece.service.StoreService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/e-commerec/v1/api")
//This is called CORS Configuration (CROSS ORIGIN RESOURCE SHARE)
@CrossOrigin(allowCredentials = "true",origins = "http://localhost:5173/")
public class StoreController 
{
	private StoreService service;
	
	@PostMapping("/stores/{sellerId}")
	public ResponseEntity<ResponseStructure<StoreResponse>> addStore(@RequestBody StoreRequest request,@PathVariable int sellerId)
	{
		  return service.addStore(request,sellerId);
	}
	
	@PutMapping("/stores/{storeId}")
	public ResponseEntity<ResponseStructure<StoreResponse>> updateStore(@RequestBody StoreRequest request,@PathVariable int storeId)
	{
		return service.updateStore(request,storeId);
	}
	
	@GetMapping("/stores/{storeId}")
	public ResponseEntity<ResponseStructure<StoreResponse>> findStoreById(@PathVariable int storeId)
	{
		return service.findStoreById(storeId);
	}
	
	@GetMapping("/stores/{sellerId}/seller")
	public ResponseEntity<ResponseStructure<StoreResponse>> findStoreBySeller(@PathVariable int sellerId)
	{
		return service.findStoreBySeller(sellerId);
	}
}
