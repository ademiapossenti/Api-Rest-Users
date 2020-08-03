package com.xdomain.user.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		
		log.info(">>>>>>>>>>>>>>>>>>>> HEADER ----- {}" ,header);
		
		header = header.replace("Bearer ", "");
		
		log.info(">>>>>>>>>>>>>>>>>>>> HEADER-PROCESADO ----- {}" ,header);
		
		boolean validToken = false;
		Claims token = null;
		try {
			
			 JwtParser jwtParser = Jwts.parser().setSigningKey("Mi.clave.Secreta.12345.JJ".getBytes());
			
			 log.info(">>>>>>>>>>>>>>>>>>>> PARSEANDO HEADER JWT ----- {}" ,jwtParser.toString());
			 
			 Jws<Claims> claimsJws = jwtParser.parseClaimsJws(header);
			 
			 log.info(">>>>>>>>>>>>>>>>>>>> PARSEANDO LOS CLAIMS DEL  HEADER JWT ----- {}" ,claimsJws);
			 
			 Claims claims = claimsJws.getBody();
			 
			 log.info(">>>>>>>>>>>>>>>>>>>> OBTENIENDO LOS CLAIMS DEL  HEADER JWT ----- {}" ,claims);
			 
			 token = claims;
			 
			 log.info(">>>>>>>>>>>>>>>>>>>> TOKEN/CLAIMS ----- {}" ,claims);
			
			validToken = true;
			
			 log.info(">>>>>>>>>>>>>>>>>>>>TOKEN VALIDO?? ----- {}" ,validToken);
		}catch(JwtException | IllegalArgumentException e) {
			log.error(e.getMessage());
			e.printStackTrace();
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
		//Manejamos el contexto de seguridad, autentica al usaurio dentro del request.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
		
	}

	
	
}
