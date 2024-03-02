package com.clone.ecommerece.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clone.ecommerece.entity.Product;
import com.clone.ecommerece.exception.ProductNotFoundByIdException;
import com.clone.ecommerece.repo.ProductRepo;
import com.clone.ecommerece.requestDto.ProductRequest;
import com.clone.ecommerece.responseDto.ProductResponse;
import com.clone.ecommerece.service.ProductService;
import com.clone.ecommerece.util.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService
{
	private ProductRepo productRepo;
	private ResponseStructure<ProductResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<ProductResponse>> addProduct(ProductRequest productRequest) {
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMsg("Product Saved Sucessfull!!");
		responseStructure.setData(mapToResponse(productRepo.save(mapToProduct(productRequest))));
		return new ResponseEntity<ResponseStructure<ProductResponse>>(responseStructure,HttpStatus.OK);
		
	}
	
	@Override
	public ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(ProductRequest productRequest, int productId) {
		return productRepo.findById(productId).map(product->{
			Product product2 = mapToProduct(productRequest);
			product2.setProductId(productId);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMsg("Product Saved Sucessfull!!");
			responseStructure.setData(mapToResponse(productRepo.save(product2)));
			return new ResponseEntity<ResponseStructure<ProductResponse>>(responseStructure,HttpStatus.OK);
		}).orElseThrow(()-> new ProductNotFoundByIdException("Invalid product Id"));
	}
	
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private ProductResponse mapToResponse(Product product) {
		return ProductResponse.builder()
				.productName(product.getProductName())
				.productDescription(product.getProductDescription())
				.productPrice(product.getProductPrice())
				.status(product.getStatus())
				.averageRating(product.getAverageRating())
				.totalOrders(product.getTotalOrders())
				.build();
	}

	private Product mapToProduct(ProductRequest productRequest) {
		return Product.builder()
				.productName(productRequest.getProductName())
				.productDescription(productRequest.getProductDescription())
				.productPrice(productRequest.getProductPrice())
				.status(productRequest.getStatus())
				.averageRating(productRequest.getAverageRating())
				.totalOrders(productRequest.getTotalOrders())
				.build();
	}


}
