package com.xdomain.user.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.xdomain.user.entities.PhoneEntity;
import com.xdomain.user.entities.UserEntity;
import com.xdomain.user.model.ResponseUserStatus;
import com.xdomain.user.model.UserRequest;
import com.xdomain.user.model.UserResponse;
import com.xdomain.user.repository.UserRepository;
import com.xdomain.user.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	
	@TestConfiguration
	static class UserServiceImplTestContextConfiguration {
		
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
		
	}
	
	@Autowired(required = true )
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	UserRequest userRequest = null;
	UserResponse userResponse = null;
	List<PhoneEntity> phones = null;
	UserResponse found = null;
	
	
	
	@Test
	public void getAllUserTest() {
		
		
		phones = new ArrayList<PhoneEntity>();
		phones.add(new PhoneEntity("1111", "11", "314"));
		
		List <UserEntity>users = new ArrayList<UserEntity>();
		users.add(new UserEntity(new UUID(0,10), "Test1", "test@test.com", "Test12", phones, LocalDateTime.now(), LocalDateTime.now(), true));
		
		List<UserResponse> found = new ArrayList<UserResponse>();
		when(userRepository.findAll()).thenReturn(users);
		found = userService.getUsers();
		assertThat(found).isNotEmpty();
	}
	
	
	@Test
	public void getUserByIdTest() {
		
		
		when(userService.getUserById(userResponse.getId())).thenReturn(userResponse);
		
		UserResponse foundUser = userService.getUserById(userResponse.getId());
		
		assertThat(foundUser).isNotNull();
	}
	
	@Test
	public void createUserTest() {
		
		userRequest = new UserRequest();
		userRequest.setId(new UUID(0,3));
		userRequest.setName("test3");
		userRequest.setPassword("test12");
		userRequest.setEmail("test3@test.com");
		
		
		when(userService.createUser(userRequest)).thenReturn(userResponse);
		found = userService.createUser(userRequest);
		
		assertThat(found).isNotNull();
		
	}
	
	@Test
	public void updateUserTest() {
		
		userRequest = new UserRequest();
		userRequest.setName("test4");
		userRequest.setPassword("test14");
		userRequest.setEmail("test4@test.com");
		
		ResponseUserStatus responseStatus = new ResponseUserStatus("USR-200", "User TEST  updated!");
		
		when(userService.updateUser(userRequest.getId(), userRequest)).thenReturn(responseStatus);
		ResponseUserStatus found = userService.updateUser(userRequest.getId(), userRequest);
		
		assertThat(found.getCode().equals("USR-200"));
		
	}
	
	
}
