package com.clone.ecommerece.responseDto;

import java.util.List;

import com.clone.ecommerece.entity.Contact;
import com.clone.ecommerece.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse 
{
	private int addressId;
	private String streetAddress;
	private String streetAddressAdditional;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private AddressType addressType;
	private List<Contact> contactList;
}
