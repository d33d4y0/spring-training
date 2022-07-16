package com.github.d33d4y0.training.mvc.authfilter.service;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtService {

	public static Claims extractAllClaims(String token) {
		try {
			return Jwts.parser().setSigningKey("secret".getBytes("UTF-8")).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException | UnsupportedEncodingException e) {
			throw new RuntimeException("cannot extract claims", e);
		}
	}
	
	public String generateJwt(String role) throws UnsupportedEncodingException {
		return Jwts.builder().claim("role", role).claim("name", "d33d4y").setIssuedAt(Date.valueOf(LocalDate.now()))
				.setExpiration(Date.valueOf(LocalDate.now().plusDays(1)))
				.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8")).compact();
	}
}
