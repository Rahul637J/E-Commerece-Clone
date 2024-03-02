package com.clone.ecommerece.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clone.ecommerece.entity.RefreshToken;
import com.clone.ecommerece.entity.User;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long>{

	Optional<RefreshToken> findByToken(String rt);

		List<RefreshToken> findAllByUserAndIsBlockedAndTokenNot(User user, boolean b, String rt);

		List<RefreshToken> findByExpirationBefore(LocalDateTime now);

		Optional<RefreshToken> findByUserAndIsBlocked(User user, boolean b);

		boolean existsByTokenAndIsBlockedAndExpirationAfter(String refreshToken, boolean b, LocalDateTime now);

		boolean existsByTokenAndIsBlocked(String rt, boolean b);

}
