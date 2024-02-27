package com.clone.ecommerece.serviceimpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clone.ecommerece.entity.Contact;
import com.clone.ecommerece.exception.AddressNotFoundById;
import com.clone.ecommerece.exception.ContactAlreadyExistException;
import com.clone.ecommerece.exception.ContactNotFindById;
import com.clone.ecommerece.repo.AddressRepo;
import com.clone.ecommerece.repo.ContactRepo;
import com.clone.ecommerece.requestDto.ContactRequest;
import com.clone.ecommerece.responseDto.ContactResponse;
import com.clone.ecommerece.service.ContactService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService
{
	private ContactRepo contactRepo;
	private ResponseStructure<ContactResponse> responseStructure;
	private AddressRepo addressRepo;
	
	@Override
	public ResponseEntity<ResponseStructure<ContactResponse>> addContact(ContactRequest request,int addressId) {
		   return addressRepo.findById(addressId).map(address->{
			   if(address.getContactList().size()<2) {
			Contact contact = mapToContact(request);
			contact.setAddress(address);
			contactRepo.save(contact);
			address.getContactList().add(contact);
			addressRepo.save(address);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMsg("Contact saved successfully");
			responseStructure.setData(mapToResponse(contact));
			return new ResponseEntity<ResponseStructure<ContactResponse>>(responseStructure,HttpStatus.CREATED);
			   }
			   else
				   throw new ContactAlreadyExistException("One address can have 2 contacts");
		}).orElseThrow(()-> new AddressNotFoundById("Invalid Address ID"));  
	}

	@Override
	public ResponseEntity<ResponseStructure<ContactResponse>> updateContact(ContactRequest request, int contactId) {
		return contactRepo.findById(contactId).map(contact->{
			Contact contact2 = mapToContact(request);
			contact2.setContactId(contactId);
			contact2.setAddress(contact.getAddress());
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMsg("Contact saved successfully");
			responseStructure.setData(mapToResponse(contactRepo.save(contact2)));
			return new ResponseEntity<ResponseStructure<ContactResponse>>(responseStructure,HttpStatus.CREATED);
		}).orElseThrow(()-> new ContactNotFindById("Invalid Contact ID"));
	}

	@Override
	public ResponseEntity<ResponseStructure<ContactResponse>> findContactById(int contactId) {
		return contactRepo.findById(contactId).map(contact->{
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMsg("Contact saved successfully");
			responseStructure.setData(mapToResponse(contact));
			return new ResponseEntity<ResponseStructure<ContactResponse>>(responseStructure,HttpStatus.OK);
		}).orElseThrow(()-> new ContactNotFindById("Invalid Contact ID"));
	}

	@Override
	public ResponseEntity<ResponseStructure<ContactResponse>> findContactByAddress(int addressId) {
		return addressRepo.findById(addressId).map(address->{
			    List<Contact> contactList = address.getContactList();
			    for(Contact contact:contactList)
			    {
			    	responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMsg("Contact saved successfully");
					responseStructure.setData(mapToResponse(contact));
					return new ResponseEntity<ResponseStructure<ContactResponse>>(responseStructure,HttpStatus.OK);
			    }
			    return new ResponseEntity<ResponseStructure<ContactResponse>>(responseStructure,HttpStatus.OK);
		}).orElseThrow(()-> new AddressNotFoundById("Invalid Store Id"));
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------//
	
	private Contact mapToContact(ContactRequest request) {
		return Contact.builder()
				.contactName(request.getContactName())
				.contactNumber(request.getContactNumber())
				.priority(request.getPriority())
				.build();
	}
	
	private ContactResponse mapToResponse(Contact save) {
		return ContactResponse.builder()
				.contactId(save.getContactId())
				.address(save.getAddress())
				.contactName(save.getContactName())
				.contactNumber(save.getContactNumber())
				.priority(save.getPriority())
				.build();
	}
}
