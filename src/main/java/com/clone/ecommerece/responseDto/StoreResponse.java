package com.clone.ecommerece.responseDto;

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
public class StoreResponse {
	
	private int storeId;
	private String storeName;
	private String storeAbout;
	private String storeLogoLink;

}
