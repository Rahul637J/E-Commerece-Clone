package com.clone.ecommerece.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clone.ecommerece.entity.Address;
import com.clone.ecommerece.entity.Customer;
import com.clone.ecommerece.entity.Seller;
import com.clone.ecommerece.entity.Store;
import com.clone.ecommerece.exception.AddressNotFoundById;
import com.clone.ecommerece.exception.SellerNotHavingStoreException;
import com.clone.ecommerece.exception.StoreAlreadyHasAddressException;
import com.clone.ecommerece.exception.StoreNotFoundByIdException;
import com.clone.ecommerece.repo.AddressRepo;
import com.clone.ecommerece.repo.StoreRepo;
import com.clone.ecommerece.repo.UserRepo;
import com.clone.ecommerece.requestDto.AddressRequest;
import com.clone.ecommerece.responseDto.AddressResponse;
import com.clone.ecommerece.service.AddressService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService{
	
	private AddressRepo addressRepo;
	private ResponseStructure<AddressResponse> responseStructure;
	private StoreRepo storeRepo;
	
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest request,int storeId) {
		return storeRepo.findById(storeId).map(store->{
			if(store.getAddress()==null) {
			Address address = mapToAddress(request);
			store.setAddress(address);
			addressRepo.save(address);
			storeRepo.save(store);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMsg("Address Saved to store successfully");
			responseStructure.setData(mapToResponse(address));
			}
			return new ResponseEntity<ResponseStructure<AddressResponse>>(responseStructure,HttpStatus.OK);

		}).orElseThrow(()-> new StoreNotFoundByIdException("Store Not Found By Id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest request, int addressId) {
		return addressRepo.findById(addressId).map(address->{
			Address address2 = mapToAddress(request);
			address2.setAddressId(addressId);
			addressRepo.save(address2);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMsg("Address Saved to store successfully");
			responseStructure.setData(mapToResponse(address2));
			return new ResponseEntity<ResponseStructure<AddressResponse>>(responseStructure,HttpStatus.CREATED);
	}).orElseThrow(()-> new AddressNotFoundById("Invalid Address ID"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(int addressId) {
		return addressRepo.findById(addressId).map(address->{
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMsg("Address Saved to store successfully");
				responseStructure.setData(mapToResponse(address));
				return new ResponseEntity<ResponseStructure<AddressResponse>>(responseStructure,HttpStatus.OK);
		}).orElseThrow(()-> new AddressNotFoundById("Invalid Address ID"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddressByStore(int storeId) {
		return storeRepo.findById(storeId).map(store->{
			if(store.getAddress()==null) {
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMsg("Address Saved to store successfully");
			responseStructure.setData(mapToResponse(store.getAddress()));
			}
			return new ResponseEntity<ResponseStructure<AddressResponse>>(responseStructure,HttpStatus.CREATED);

		}).orElseThrow(()-> new StoreNotFoundByIdException("Store Not Found By Id"));
	}

//-----------------------------------------------------------------------------------------------------------------------------------------------//
	
	private Address mapToAddress(AddressRequest request) {
		return Address.builder()
				.addressType(request.getAddressType())
				.streetAddress(request.getStreetAddress())
				.streetAddressAdditional(request.getStreetAddressAdditional())
				.city(request.getCity())
				.state(request.getState())
				.country(request.getCountry())
				.pincode(request.getPincode())
				.build();			
	}
	
	private AddressResponse mapToResponse(Address save) {
		return AddressResponse.builder()
				.addressId(save.getAddressId())
				.streetAddress(save.getStreetAddress())
				.streetAddressAdditional(save.getStreetAddressAdditional())
				.addressType(save.getAddressType())
				.city(save.getCity())
				.state(save.getState())
				.country(save.getCountry())
				.contactList(save.getContactList())
				.pincode(save.getPincode())
				.build();
	}
}
