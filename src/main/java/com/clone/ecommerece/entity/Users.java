package com.clone.ecommerece.entity;

import com.clone.ecommerece.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED) //Here we are using is-a-relation rather than has-a-relation for that we use this and strategy it will create new table using this 
public class Users 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usersId;
	private String userName;
	private String email;
	private String password;
	private UserRole userRole;
	private boolean isEmailVerified;
	private boolean isDeleted;	
}
