package com.clone.ecommerece.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sellers")
public class Seller extends User
{
	@OneToOne
	private Store store;
}
