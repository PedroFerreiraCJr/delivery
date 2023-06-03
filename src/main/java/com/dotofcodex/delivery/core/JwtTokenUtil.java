package com.dotofcodex.delivery.core;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	private final String secret;

	public JwtTokenUtil(@Value("${jwt.secret}") String secret) {
		super();
		this.secret = secret;
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> resolverFn) {
		Claims claims = getAllClaimsFromToken(token);
		return resolverFn.apply(claims);
	}

	private Boolean isTokenExpired(String token) {
		Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

    public Boolean validateToken(String token, UserDetails details) {
        String username = getUsernameFromToken(token);
        return (username.equals(details.getUsername()) && !isTokenExpired(token));
	}

	public String generateToken(Authentication authentication) {
		Map<String, Object> claims = new HashMap<>();
		UserDetails user = (UserDetails) authentication.getPrincipal();

		return generateToken(claims, user.getUsername());
	}

	public String generateToken(Map<String, Object> claims, final String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(Instant.now().plus(8, ChronoUnit.HOURS)))
				.signWith(SignatureAlgorithm.HS512, this.secret)
				.compact();
    }

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}
}
