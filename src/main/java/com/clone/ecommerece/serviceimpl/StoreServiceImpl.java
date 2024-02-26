package com.clone.ecommerece.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clone.ecommerece.entity.Store;
import com.clone.ecommerece.enums.UserRole;
import com.clone.ecommerece.exception.StoreNotFoundByIdException;
import com.clone.ecommerece.repo.SellerRepo;
import com.clone.ecommerece.repo.StoreRepo;
import com.clone.ecommerece.requestDto.StoreRequest;
import com.clone.ecommerece.responseDto.StoreResponse;
import com.clone.ecommerece.service.StoreService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService{

	private StoreRepo storeRepo;
	private ResponseStructure<StoreResponse> responseStructure;
	private SellerRepo sellerRepo;

	@Override
	public ResponseEntity<ResponseStructure<StoreResponse>> addStore(StoreRequest request,int sellerId) {
		return sellerRepo.findById(sellerId).map(seller->{
			Store store = mapToStore(request);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMsg("Store Data Saved Successfully");
			responseStructure.setData(MapToResponse(storeRepo.save(store)));
			seller.setStore(store);
			sellerRepo.save(seller);
			return new ResponseEntity<ResponseStructure<StoreResponse>>(responseStructure,HttpStatus.CREATED);
		}).orElseThrow(()-> new UsernameNotFoundException("Invaliad Seller ID"));
	}

	@Override
	public ResponseEntity<ResponseStructure<StoreResponse>> updateStore(StoreRequest request, int storeId) {
		return storeRepo.findById(storeId).map(store->{
			Store store2 = mapToStore(request);
			store2.setStoreId(storeId);
			store2.setAddress(store.getAddress());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMsg("Store Data has been updated Successfully");
			responseStructure.setData(MapToResponse(storeRepo.save(store2)));
			return new ResponseEntity<ResponseStructure<StoreResponse>>(responseStructure,HttpStatus.OK);
		}).orElseThrow(()-> new StoreNotFoundByIdException("Invalid Store Id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<StoreResponse>> findStoreById(int storeId) {
		return storeRepo.findById(storeId).map(store->{
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMsg("Store Data Saved Successfully");
			responseStructure.setData(MapToResponse(store));
			return new ResponseEntity<ResponseStructure<StoreResponse>>(responseStructure,HttpStatus.OK);
		}).orElseThrow(()-> new StoreNotFoundByIdException("Invalid Store Id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<StoreResponse>> findStoreBySeller(int sellerId) 
	{
		return sellerRepo.findById(sellerId).map(user->{
			if(user.getUserRole()==UserRole.SELLER) {
				responseStructure.setStatus(HttpStatus.FOUND.value());
				responseStructure.setMsg("Store Data Saved Successfully");
				responseStructure.setData(MapToResponse(user.getStore()));
			}
			return new ResponseEntity<ResponseStructure<StoreResponse>>(responseStructure,HttpStatus.FOUND);
		}).orElseThrow(()-> new UsernameNotFoundException("Invalid User Id"));
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------------//

	private Store mapToStore(StoreRequest request) {
		return Store.builder()
				.storeName(request.getStoreName())
				.storeAbout(request.getStoreAbout())
				.storeLogoLink(request.getStoreLogoLink())
				.build();
	}

	private StoreResponse MapToResponse(Store save) {
		return StoreResponse.builder()
				.storeId(save.getStoreId())
				.storeName(save.getStoreName())
				.storeAbout(save.getStoreAbout())
				.storeLogoLink(save.getStoreLogoLink())
				.build();
	}
}
