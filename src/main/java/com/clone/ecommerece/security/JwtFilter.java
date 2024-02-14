package com.clone.ecommerece.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.clone.ecommerece.entity.AccessToken;
import com.clone.ecommerece.exception.ConstraintViolationException;
import com.clone.ecommerece.exception.CookiesNotCreatedException;
import com.clone.ecommerece.exception.UserNotLoggedInException;
import com.clone.ecommerece.repo.AccessTokenRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j      //This is for the log management 
@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
	
	private AccessTokenRepo accessTokenRepo;
	private JwtService jwtService;
	private CustomAuthDetailsService authDetailsService;
	
	//THIS IS THE CUSTOM FILTER WHICH WE USED TO AUTHENTICATE THE CRENDITALS AND PASS THE REQUEST FOR THE FURTHER PROCESS 
	//NOTE- THERE IS NO ANY PRE-DEFINED WAY TO CREATE A FILTER THE CUSTOM FILTER IS ONLY CREATED BY OUR OWN LOGIC 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		String at = null;
		String rt = null;
		Cookie[] cookies = request.getCookies();
		log.info("In Jwt filter");
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("at"))
					at = cookie.getValue();
				if (cookie.getName().equals("rt"))
					rt = cookie.getValue();
			}
			log.info("Trying Authenticating the token.....");
			String username = null;
			if (at != null && rt != null) {
				Optional<AccessToken> accesstoken = accessTokenRepo.findByTokenAndIsBlocked(at, false);
				if (accesstoken == null)
					throw new UserNotLoggedInException("Token is blocked");
				else {
					log.info("Authenticating the token.....");
					username = jwtService.extractUserName(at);
					if (username == null)
						throw new ConstraintViolationException("Failed to Authenticate");
					 UserDetails userDetails = authDetailsService.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							username, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					log.info("Authenticated Succesfully");
				}
			}
		}
		filterChain.doFilter(request, response); //THIS WILL DELICATE THE FILTER PROCESS FOR FURTHER INBUILT FILTERS 
	}
}

//NOTE MAP() does not allow us to reinitialize the variable which we assigned outer block
