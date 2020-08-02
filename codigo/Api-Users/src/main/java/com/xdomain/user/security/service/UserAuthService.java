package com.xdomain.user.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xdomain.user.exception.UserAuthenticationException;
import com.xdomain.user.security.entities.RoleAuthEntity;
import com.xdomain.user.security.entities.UserAuthEntity;
import com.xdomain.user.security.respository.UserAuthRepository;
import com.xdomain.user.security.service.UserAuthService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserAuthService implements UserDetailsService{

	@Autowired
	UserAuthRepository userRepository;


	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		log.info(">>>>>>>>>>>>>>>> USER {}", username);
		UserAuthEntity user = userRepository.findByUsername(username);
		
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("Username %s not found", username));		
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		for(RoleAuthEntity role: user.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		if(roles.isEmpty()) {
			throw new UserAuthenticationException("The username %s no has any role assigned");
		}
		
		return new User(username, user.getPassword(), user.getEnabled(), true, true, true, roles);
		
	}
	
	
	
}
