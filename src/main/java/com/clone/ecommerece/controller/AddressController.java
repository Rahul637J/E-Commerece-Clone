package com.clone.ecommerece.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clone.ecommerece.requestDto.AddressRequest;
import com.clone.ecommerece.responseDto.AddressResponse;
import com.clone.ecommerece.service.AddressService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/e-commerec/v1/api")
//This is called CORS Configuration (CROSS ORIGIN RESOURCE SHARE)
@CrossOrigin(allowCredentials = "true",origins = "http://localhost:5173/")
public class AddressController 
{
	private AddressService addressService;
	
	@PostMapping("/address/{storeId}")
	@PreAuthorize(value = "hasAuthority('SELLER')")
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@RequestBody AddressRequest request,@PathVariable int storeId)
	{
		return addressService.addAddress(request,storeId);
	}
	
//	@PostMapping("/address")
//	@PreAuthorize(value = "hasAuthority('CUSTOMER')")
//	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@RequestBody AddressRequest request)
//	{
//		return addressService.addAddress(request,storeId);
//	}
	
	@PutMapping("/address/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@RequestBody AddressRequest request,@PathVariable int addressId)
	{
		return addressService.updateAddress(request,addressId);
	}
	
	@GetMapping("/address/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(@PathVariable int addressId)
	{
		return addressService.findAddressById(addressId);
	}
	
	@GetMapping("/address/{storeId}/store")
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddressByStore(@PathVariable int storeId)
	{
		return addressService.findAddressByStore(storeId);
	}

}
