package com.clone.ecommerece.entity;

import java.util.List;

import com.clone.ecommerece.enums.AddressType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private String streetAddress;
	private String streetAddressAdditional;
	private String city;
	private String state;
	private String country;
	private int pincode;
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
	
	@OneToMany(mappedBy = "address",fetch = FetchType.EAGER)
	private List<Contact> contactList;
	
	@ManyToOne
	private Customer customer;
}
