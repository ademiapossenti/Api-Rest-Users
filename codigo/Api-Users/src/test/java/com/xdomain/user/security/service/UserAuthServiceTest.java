package com.xdomain.user.security.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.xdomain.user.security.entities.RoleAuthEntity;
import com.xdomain.user.security.entities.UserAuthEntity;
import com.xdomain.user.security.respository.UserAuthRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
class UserAuthServiceTest {

	
	@Autowired(required = true )
	UserAuthService userService;
	
	
	@MockBean
	UserAuthRepository userRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testLoadUserByUsername() {
		
		List<RoleAuthEntity> roles = new ArrayList<RoleAuthEntity>();
		
		roles.add(new RoleAuthEntity(1L, "admin", new ArrayList<UserAuthEntity>()));
		
		when(userRepository.findByUsername(Matchers.eq("test"))).thenReturn(new UserAuthEntity(1L,"test","test1",true, roles));
		UserDetails ret = userService.loadUserByUsername("test");
		
		assertEquals("test", ret.getUsername());
		
		assertEquals("test1", ret.getPassword());
		assertTrue(ret.isEnabled());
		
		
		
	}

}
