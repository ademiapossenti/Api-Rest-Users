package com.xdomain.user.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
	
		String header = request.getHeader("Authorization");
		
		if(header == null || !header.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		
		header = header.replace("Bearer ", "");
		
		
		boolean validToken = false;
		Claims token = null;
		try {
			
			
	
			
			 JwtParser jwtParser = Jwts.parser().setSigningKey("Mi.clave.Secreta.12345.JJ".getBytes());
			 Jws<Claims> claimsJws = jwtParser.parseClaimsJws(header);
			 Claims claims = claimsJws.getBody();
			 token = claims;
			
			validToken = true;
		}catch(ArrayIndexOutOfBoundsException |JwtException | IllegalArgumentException e) {
			Map<String, Object> body = new HashMap<String, Object>();
			body.put("description", "Forbidden");
			body.put("code", "USR-" + HttpStatus.FORBIDDEN);
			response.getWriter().write(new ObjectMapper().writeValueAsString(body));
			
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setContentType("application/json");
			
			return;
		}
		

		UsernamePasswordAuthenticationToken authentication = null;
		
		if(validToken) {
			String username = token.getSubject();
			Object roles = token.get("authorities");
			
			
			
			//Necesario porque GrantedAuthority de spring security es una coleccion.
			Collection<? extends GrantedAuthority> authorities = 
					Arrays.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, AbstractSimpledGrantedAuthorities.class)
					.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
			
			authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
		}
		//Se maneja el contexto de seguridad, autentica al usaurio dentro del request.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
		
	}

	
	
}
