package com.clone.ecommerece.serviceimpl;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clone.ecommerece.cache.CacheStore;
import com.clone.ecommerece.entity.Customer;
import com.clone.ecommerece.entity.Seller;
import com.clone.ecommerece.entity.User;
import com.clone.ecommerece.exception.InvalidOTPException;
import com.clone.ecommerece.exception.OtpExpiredException;
import com.clone.ecommerece.exception.SessionExpiredException;
import com.clone.ecommerece.exception.UserNameAlreadyVerifiedEcxeption;
import com.clone.ecommerece.repo.CustomerRepo;
import com.clone.ecommerece.repo.SellerRepo;
import com.clone.ecommerece.repo.UserRepo;
import com.clone.ecommerece.requestDto.UsersRequest;
import com.clone.ecommerece.requestDto.otpModel;
import com.clone.ecommerece.responseDto.UserResponse;
import com.clone.ecommerece.service.AuthService;
import com.clone.ecommerece.util.MessageStructure;
import com.clone.ecommerece.util.ResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
//@NoArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService
{
	private UserRepo userRepo;
	
	private SellerRepo sellerRepo;
	
	private CustomerRepo customerRepo;
	
	private PasswordEncoder passwordEncoder;
	
	private CacheStore<String> cacheStoreOtp;
	
	private ResponseStructure<UserResponse> responseStructure;
	
	private CacheStore<User> cacheStoreuser;
	
	private JavaMailSender javaMailSender;
	
	private <T extends User> T mapToRespectiveUser(UsersRequest userRequest) 
	{
		User user=null;
		System.out.println(userRequest.getUserRole());
	    switch(userRequest.getUserRole()) {
	    case SELLER ->{user =new Seller();}
	    case CUSTOMER ->{user =new Customer();}
	    }
	    user.setEmail(userRequest.getEmail());
	    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
	    user.setUserRole(userRequest.getUserRole());
		user.setUserName(userRequest.getEmail().split("@")[0]);
//		userRepo.save(user);
		return (T) user;
	}
	
	private UserResponse mapToResponse(User saveUser) 
	{
		return UserResponse.builder()
				.usersId(saveUser.getUserId())
				.userName(saveUser.getUserName())
				.email(saveUser.getEmail())
				.isEmailVerified(saveUser.isEmailVerified())
				.userRole(saveUser.getUserRole())
				.build();	
	}
	
	private <T extends User>T saveUser(UsersRequest userRequest) 
	{
		User user=null;
		switch (userRequest.getUserRole()) {
		case CUSTOMER->{user=customerRepo.save(mapToRespectiveUser(userRequest));}
		case SELLER->{user=sellerRepo.save(mapToRespectiveUser(userRequest));}
		}
		return (T) user;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUsers(UsersRequest userRequest) 
	{
//		System.out.println(userRepo.existsByEmail(userRequest.getEmail())+" ,122");
		if(userRepo.existsByEmail(userRequest.getEmail()))throw new UserNameAlreadyVerifiedEcxeption("User Already Exists");
			String OTP=otpGenerator();
//			System.out.println(OTP);
			User user = mapToRespectiveUser(userRequest);
			cacheStoreuser.add(userRequest.getEmail(), user);
			cacheStoreOtp.add(userRequest.getEmail(), OTP);
			System.out.println(cacheStoreOtp.get(userRequest.getEmail()));
			try {
				sendOtpToMail(user, OTP);
			} catch (MessagingException e) {
				log.error("The email does not exist");
			}
			
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure
				  .setData(mapToResponse(user))
				  .setMsg("Plese verify the otp :"+OTP)
				  .setStatus(HttpStatus.ACCEPTED.value()),HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<String> verifyOtp(otpModel otp) 
	{
		System.out.println(cacheStoreOtp.get(otp.getEmail()));
		User user = cacheStoreuser.get(otp.getEmail());
		String OTP = cacheStoreOtp.get(otp.getEmail());
		System.out.println(OTP);
		if(user==null) throw new SessionExpiredException("Session expired ====> Register again");	
		if(OTP==null) throw new OtpExpiredException("OTP expired ===> click resend OTP");
//		System.out.println(otp.getOtp());
		if(!OTP.equals(otp.getOtp())) throw new InvalidOTPException("OTP mismatch");
		
		user.setEmailVerified(true);
		userRepo.save(user);
		try {
			sendResponseToMail(user);
		} catch (MessagingException e) {
			log.error("The poor internet connectivity");
		}
		return new ResponseEntity<String>("Registration successful",HttpStatus.CREATED);	
	}
	
	private String otpGenerator()
	{
		return ""+(int) (100000 + Math.random() * 900000);//God's way String.valueOf(new Random().nextInt(100000,999999)//
	}
	
	@Async
	private void sendMail(MessageStructure messageStructure) throws MessagingException
	{
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
		helper.setTo(messageStructure.getTo());
		helper.setSubject(messageStructure.getSubject());
		helper.setSentDate(messageStructure.getSentDate());
		helper.setText(messageStructure.getText(),true);
		javaMailSender.send(mimeMessage);
	}
	
	
	private void sendOtpToMail(User user,String OTP)throws MessagingException
	{

		sendMail( MessageStructure.builder()
				.to(user.getEmail())
				.subject("Complete Your Registration to E-Commerce Api")
				.sentDate(new Date())
				.text(
						"hey, "+user.getUserName()
						+"<h3>Good To See You Intrested in Our E-Commerce Api,<h3>"
						+"<h3>Complete Your Registration Using the OTP<h3> <br>"
						+"<h1>"+OTP+"<h1><br>"
						+"<h3>Note: The Method Is Expired In 5 Minutes<h3>"
						+"<br><br>"
						+"<h3>With Best Regards<h3><br>"
						+"<h1>E-Commerce Api<h1>"
						).build());
	}
	
	private void sendResponseToMail(User user)throws MessagingException
	{
		sendMail( MessageStructure.builder()
				.to(user.getEmail())
				.subject("Welcome to E-Commerce Api!")
				.sentDate(new Date())
				.text(
						"Dear, "+"<h2>"+user.getUserName()+"<h2>"
						+"<h3>Congratulations! 🎉..,Good To See You Intrested in Our E-Commerce Api,<h3>"
						+"<h3>Sucessfully Completed Your Registration to E-Commerce Api<h3> <br>"
						+"<h3>Your email has been successfully verified, and you're now officially registered with E-Commerce Api.<h3>"
						+"<br>"
						+"<h3>Let's get started on your e-commerce journey! 🚀<h3>"
						+"<br>"
						+"<h3>With Best Regards<h3><br>"
						+"<h2>Mr.Somnath<h2>"
						+"<h1>E-Commerce Api<h1>"
						).build());
	}		
}	

