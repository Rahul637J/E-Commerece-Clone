package com.clone.ecommerece.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.Address;
import com.clone.ecommerece.entity.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer>{

	    Optional<Contact> findByAddress(Address address);

}
