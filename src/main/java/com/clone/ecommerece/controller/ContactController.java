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

import com.clone.ecommerece.requestDto.ContactRequest;
import com.clone.ecommerece.responseDto.ContactResponse;
import com.clone.ecommerece.service.ContactService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/e-commerec/v1/api")
//This is called CORS Configuration (CROSS ORIGIN RESOURCE SHARE)
@CrossOrigin(allowCredentials = "true",origins = "http://localhost:5173/")
public class ContactController 
{
	private ContactService service;
	
	@PostMapping("/contacts/{addressId}")
	public ResponseEntity<ResponseStructure<ContactResponse>> addContact(@RequestBody ContactRequest request,@PathVariable int addressId)
	{
		return service.addContact(request,addressId);
	}
	
	@PutMapping("/contacts/{contactId}")
	public ResponseEntity<ResponseStructure<ContactResponse>> updateContact(@RequestBody ContactRequest request,@PathVariable int contactId)
	{
		return service.updateContact(request,contactId);
	}
	
	@GetMapping("/contacts/{contactId}")
	public ResponseEntity<ResponseStructure<ContactResponse>> findContactById(@PathVariable int contactId)
	{
		return service.findContactById(contactId);
	}
	
	@GetMapping("/contacts/{addressId}/address")
	public ResponseEntity<ResponseStructure<ContactResponse>> findContactByAddress(@PathVariable int addressId)
	{
		return service.findContactByAddress(addressId);
	}

}
