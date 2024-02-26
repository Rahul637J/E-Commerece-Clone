package com.clone.ecommerece.entity;

import com.clone.ecommerece.enums.Priority;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Contact 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contactId;
	private long contactNumber;
	private String contactName; 
	@Enumerated(EnumType.STRING)
	private Priority priority;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Address address;
}
