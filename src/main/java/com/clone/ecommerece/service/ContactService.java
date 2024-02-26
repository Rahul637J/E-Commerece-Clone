package com.clone.ecommerece.service;

import org.springframework.http.ResponseEntity;

import com.clone.ecommerece.requestDto.ContactRequest;
import com.clone.ecommerece.responseDto.ContactResponse;
import com.clone.ecommerece.util.ResponseStructure;

public interface ContactService {

	ResponseEntity<ResponseStructure<ContactResponse>> addContact(ContactRequest request,int addressId);

	ResponseEntity<ResponseStructure<ContactResponse>> updateContact(ContactRequest request, int contactId);

	ResponseEntity<ResponseStructure<ContactResponse>> findContactById(int contactId);

	ResponseEntity<ResponseStructure<ContactResponse>> findContactByAddress(int addressId);

}
