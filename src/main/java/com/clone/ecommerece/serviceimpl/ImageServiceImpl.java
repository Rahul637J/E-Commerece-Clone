package com.clone.ecommerece.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clone.ecommerece.entity.ProductImage;
import com.clone.ecommerece.entity.StoreImage;
import com.clone.ecommerece.enums.ImageType;
import com.clone.ecommerece.exception.ImageNotFoundByIdException;
import com.clone.ecommerece.exception.ProductNotFoundByIdException;
import com.clone.ecommerece.exception.StoreNotFoundByIdException;
import com.clone.ecommerece.repo.ImageRepo;
import com.clone.ecommerece.repo.ProductRepo;
import com.clone.ecommerece.repo.StoreRepo;
import com.clone.ecommerece.service.ImageService;
import com.clone.ecommerece.util.ResponseStructure;

@Service
//@AllArgsConstructor
public class ImageServiceImpl implements ImageService
{
	@Value("${baseurl}")
	private String url;
	
	private StoreRepo storeRepo;
	private ImageRepo imageRepo;
	private ProductRepo productRepo;
	private ResponseStructure<byte []> responseStructure2;
	private ResponseStructure<String> responseStructure1;
	
	public ImageServiceImpl(StoreRepo storeRepo, ImageRepo imageRepo,ProductRepo productRepo, ResponseStructure<byte[]> responseStructure2,
			ResponseStructure<String> responseStructure1) {
		super();
		this.storeRepo = storeRepo;
		this.imageRepo = imageRepo;
		this.productRepo=productRepo;
		this.responseStructure2 = responseStructure2;
		this.responseStructure1 = responseStructure1;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<String>> addStoreImage(int storeId, MultipartFile image) 
	{
		return storeRepo.findById(storeId).map(store->{
			StoreImage storeImage=new StoreImage();
			storeImage.setStoreId(storeId);
			storeImage.setImageType(ImageType.COVER);
			storeImage.setContentType(image.getContentType());
			try {
				storeImage.setImageBytes(image.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			imageRepo.save(storeImage);
			System.out.println(storeImage.getImageId());
			responseStructure1.setStatus(HttpStatus.OK.value());
			responseStructure1.setData(url+"/stores/"+storeImage.getImageId()+"/images");
			responseStructure1.setMsg("Image Added");
			return new ResponseEntity<ResponseStructure<String>>(responseStructure1,HttpStatus.OK);
		}).orElseThrow(()-> new StoreNotFoundByIdException("Invalid Store Id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> addProductImage(int productId, MultipartFile image) {
		return productRepo.findById(productId).map(product->{
			ProductImage productImage=new ProductImage();
			productImage.setProductId(productId);
			productImage.setImageType(ImageType.LOGO);
			productImage.setContentType(image.getContentType());
			try {
				productImage.setImageBytes(image.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			imageRepo.save(productImage);
			responseStructure1.setStatus(HttpStatus.OK.value());
			responseStructure1.setMsg("Image Saved Successful");
			responseStructure1.setData(url+"/products/"+productImage.getImageId()+"/images");
			return new ResponseEntity<ResponseStructure<String>>(responseStructure1,HttpStatus.OK);
		}).orElseThrow(()-> new ProductNotFoundByIdException("Invlaid Product Id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> addReviewImage(int reviewId, MultipartFile image) {
		return null;
	}

	@Override
	public ResponseEntity<byte []> getStoreImage(String imageId) 
	{
		return imageRepo.findById(imageId).map(image->{
//			 byte[] imageData = image.getImageBytes(); // Assuming you have a method to retrieve image bytes
//		        HttpHeaders headers = new HttpHeaders();
//		        headers.setContentType(MediaType.IMAGE_JPEG);
			return ResponseEntity.ok()
					.contentType(MediaType.valueOf(image.getContentType()))
					.contentLength(image.getImageBytes().length)
					.body(image.getImageBytes());
		}).orElseThrow(()-> new ImageNotFoundByIdException("Image Not Found by ID"));
	}
}
