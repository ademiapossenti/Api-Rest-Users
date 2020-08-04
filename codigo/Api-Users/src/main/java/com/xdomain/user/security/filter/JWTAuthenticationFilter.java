package com.xdomain.user.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdomain.user.security.entities.UserAuthEntity;
import com.xdomain.user.utils.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));

		String token = Jwts.builder()
				.setClaims(claims)
					.setSubject(authResult.getName())
						.signWith(SignatureAlgorithm.HS512, "Mi.clave.Secreta.12345.JJ".getBytes()).setIssuedAt(new Date())
							.setExpiration(new Date(System.currentTimeMillis() + 3600000 * 4)) // aprox 1 hora
								.compact();

		response.addHeader("Authorization", "Bearer " + token);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("user", authResult.getName());
		

		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(HttpStatus.OK.value());
		response.setContentType("application/json");

	}

	
	
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("description", "Unauthorized");
		body.put("code", Constants.USER_PREFIX_ERROR + HttpStatus.UNAUTHORIZED);
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		
		String username = obtainUsername(request);
		String password = obtainPassword(request);

		UserAuthEntity user = null;
		
		if ((username == null && password == null)) {
			try {
				
				 user = new ObjectMapper().readValue(request.getInputStream(), UserAuthEntity.class);
				 username = user.getUsername();
				 password = user.getPassword();
				
 
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(authToken);

		return authentication;
	}

}
