package com.jeremw.bookstore.auth;

import com.jeremw.bookstore.user.User;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.RequestScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 12/05/2024
 */
@RequestScoped
public class JwtService {

	@ConfigProperty(name = "mp.jwt.issuer")
	String issuer;

	@ConfigProperty(name = "auth.jwt.access-token.expiration")
	long expiration;

	public String generateToken(User user){

		return Jwt
				.upn(user.getUsername())
				.issuer("issuer")
				.expiresAt(System.currentTimeMillis()+expiration)
				.sign();

	}

}
