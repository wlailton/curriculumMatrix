package com.wlailton.curriculumMatrixapi.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	
	// EXPIRATION_TIME = 10 days
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "fnG2ttLW?>N6HBqG";
	static final String TOKEN_PREFIX = "JWT";
	static final String HEADER_STRING = "Authorization";
	
	static void addAuthentication(HttpServletResponse response, String username, Collection<? extends GrantedAuthority> authority) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", authority.stream().map(s -> s.toString()).collect(Collectors.toList()));
		String JWT = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}
	
	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			Claims claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
					
			String user = claims.getSubject();
			List<String> roles = claims.get("roles", List.class);
	        List<GrantedAuthority> authorities = roles.stream()
	                .map(authority -> new SimpleGrantedAuthority(authority))
	                .collect(Collectors.toList());
			
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, authorities);
			}
		}
		return null;
	}
	

	
}
