package com.clone.ecommerece.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.clone.ecommerece.enums.ImageType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "image")
@Setter
@Getter
public class Image {
	@Id
	private String imageId;
	@Enumerated(EnumType.STRING)
	private ImageType imageType;
	private byte[] imageBytes;
}
