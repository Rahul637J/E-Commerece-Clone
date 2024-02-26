package com.clone.ecommerece.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clone.ecommerece.requestDto.AuthRequest;
import com.clone.ecommerece.requestDto.OtpModel;
import com.clone.ecommerece.requestDto.UsersRequest;
import com.clone.ecommerece.responseDto.AuthResponse;
import com.clone.ecommerece.responseDto.UserResponse;
import com.clone.ecommerece.service.AuthService;
import com.clone.ecommerece.util.ResponseStructure;
import com.clone.ecommerece.util.SimpleReponse;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/e-commerec/v1/api")
//This is called CORS Configuration (CROSS ORIGIN RESOURCE SHARE)
@CrossOrigin(allowCredentials = "true",origins = "http://localhost:5173/")
public class AuthController 
{
	private AuthService authService;
	
	@PostMapping("/users")
	public  ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody UsersRequest userRequest)
	{
		System.out.println(userRequest.getEmail()+","+userRequest.getPassword()+","+userRequest.getUserRole());
		 return authService.addUsers(userRequest);
	}
	
//	@PreAuthorize(value = "hasAuthority('CUSTOMER') or hasAuthority('SELLER')")
	@PostMapping("/users/verifyotp")
	public ResponseEntity<String> verifyOtp(@RequestBody OtpModel otp)
	{
		return authService.verifyOtp(otp);
	}
	
//	@PreAuthorize(value = "hasAuthority('CUSTOMER') or hasAuthority('SELLER')")
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<AuthResponse>> login(@CookieValue(value = "at",required = false) String at,
			@CookieValue (value = "rt",required = false)String rt,@RequestBody AuthRequest authRequest,HttpServletResponse httpServletResponse)
	{
		System.out.println("Logged In");
		return authService.login(at,rt,authRequest,httpServletResponse);
	}
	
//	@PreAuthorize(value = "hasAuthority('CUSTOMER') OR hasAuthority('SELLER')")
	@PostMapping("/logout")
	public ResponseEntity<ResponseStructure<String>>logout(@CookieValue(value = "at",required = false) String at,
			@CookieValue (value = "rt",required = false)String rt,HttpServletResponse httpServletResponse)
	{
		return authService.logout(at,rt,httpServletResponse);
	}
	
	@PreAuthorize(value = "hasAuthority('CUSTOMER') OR hasAuthority('SELLER')")
	@PostMapping("/revoke-all")
	public ResponseEntity<SimpleReponse> revokeOther(@CookieValue(value = "at",required = false) String at,
			@CookieValue (value = "rt",required = false)String rt,HttpServletResponse httpServletResponse)
	{
		return authService.revokeOther(at,rt,httpServletResponse);
	}
	
	@PreAuthorize(value = "hasAuthority('SELLER') OR hasAuthority('CUSTOMER')")
	@PostMapping("/revoke-other")
	public ResponseEntity<SimpleReponse> revokeOtherDeviceAccess(
			@CookieValue(name = "rt", required = false) String refreshToken,
			@CookieValue(name = "at", required = false) String accessToken) {
		return authService.revokeOtherDeviceAccess(accessToken, refreshToken);
	}
	
	@PreAuthorize(value = "hasAuthority('SELLER') OR hasAuthority('CUSTOMER')")
	@PostMapping("/refresh-login/token-rotation")
	public ResponseEntity<ResponseStructure<AuthResponse>> refreshToken(@CookieValue(name = "rt", required = false) String refreshToken,
			@CookieValue(name = "at", required = false) String accessToken,HttpServletResponse httpServletResponse)
	{
		return authService.refreshToken(accessToken,refreshToken,httpServletResponse);
	}
}
