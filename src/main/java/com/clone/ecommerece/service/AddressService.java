package com.clone.ecommerece.service;

import org.springframework.http.ResponseEntity;

import com.clone.ecommerece.requestDto.AddressRequest;
import com.clone.ecommerece.responseDto.AddressResponse;
import com.clone.ecommerece.util.ResponseStructure;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest request,int storeId);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest request, int addressId);

	ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(int addressId);

	ResponseEntity<ResponseStructure<AddressResponse>> findAddressByStore(int storeId);

}
