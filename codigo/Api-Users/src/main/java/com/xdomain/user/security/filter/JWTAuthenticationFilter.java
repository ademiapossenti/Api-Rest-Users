package com.xdomain.user.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;

	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		if(username ==null) {
			username = "";
		}
		
		if(password == null) {
			password = "";
		}
		
		
		
		username = username.trim();
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authToken);
	}
	
	

}
