package com.clone.ecommerece.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;


@Document
@Setter
@Getter
public class StoreImage extends Image{
	private int storeId;
}
