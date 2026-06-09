package com.example.emplyee_manager_sysetm.Security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.emplyee_manager_sysetm.emus.Roles;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtiles {
	@Value("${jwt.secret}")
	private String securit;
	@Value("${jwt.expiration}")
	private long expiration;

	private SecretKey getSignedKey() {
		return Keys.hmacShaKeyFor(securit.getBytes());
	};

	public String generateToken(String userName, Roles role) {
		String Token = Jwts.builder().claim("Role", role).subject(userName).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration)).signWith(getSignedKey()).compact();
		return Token;
	}

	public String extractRole(String token) {

		String Berer = token.replace("Bearer ", "");
		String role = Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(Berer).getPayload().get("Role",
				String.class);
		return role;
	}

	public String extractUsername(String token) {
		String BearerRemoveToken = token.replace("Bearer ", "");
		String userName = Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(BearerRemoveToken)
				.getPayload().getSubject();
		return userName;
	}

	public boolean validToken(String token, String userName) {
		String extractUserName = extractUsername(token);
		if (!extractUserName.equals(userName)) {
			return false;
		}
		Date time = new Date(System.currentTimeMillis());
		String Bearer = token.replace("Bearer ", "");
		Date ExpTime = Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(Bearer).getPayload()
				.getExpiration();
		if (time.after(ExpTime)) {
			return false;
		}
		return true;
	}
}
