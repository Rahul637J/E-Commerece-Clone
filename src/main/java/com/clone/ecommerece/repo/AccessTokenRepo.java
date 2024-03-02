package com.clone.ecommerece.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.AccessToken;
import com.clone.ecommerece.entity.User;

public interface AccessTokenRepo extends JpaRepository<AccessToken, Long>
{
	Optional<AccessToken> findByToken(String at);
	
	Optional<AccessToken> findByTokenAndIsBlocked(String at, boolean b);

	List<AccessToken> findAllByUserAndIsBlockedAndTokenNot(User user, boolean b, String at);

	List<AccessToken> findByExpirationBefore(LocalDateTime dateTime);

	Optional<AccessToken> findByUserAndIsBlocked(User user, boolean b);
	
}
