package com.clone.ecommerece.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Setter
@Getter
public class ReviewImage extends Image{
 private String reviewId;
}
