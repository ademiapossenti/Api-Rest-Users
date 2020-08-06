package com.xdomain.user.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdomain.user.entities.PhoneEntity;
import com.xdomain.user.entities.UserEntity;
import com.xdomain.user.exception.UserException;
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
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Test
	public void getAllUserTest() {
		
		
		phones = new ArrayList<PhoneEntity>();
		phones.add(new PhoneEntity("1111", "11", "314"));
		
		List <UserEntity>users = new ArrayList<UserEntity>();
		users.add(new UserEntity(new UUID(0,10), "Test1", "test@test.com", "Test12", phones, LocalDateTime.now(), LocalDateTime.now(), true));
		

		when(userRepository.findAll()).thenReturn(users);
		
		List<UserResponse> found = userService.getUsers();
		assertThat(found).isNotEmpty();
	}
	
	
	
	@Test
	public void getUserByIdTest() {
		
		
		phones = new ArrayList<PhoneEntity>();
		Optional<UserEntity> userEntity = Optional.ofNullable(new UserEntity(new UUID(0,10), "Test1", "test@test.com", "Test12", phones, LocalDateTime.now(), LocalDateTime.now(), true));
		
		when(userRepository.findById(Matchers.any())).thenReturn(userEntity);
		
		UserResponse foundUser = userService.getUserById(userEntity.get().getId());
		
		assertNotNull(foundUser);
	}
	
	
	@Test
	public void createUserTest() {
		
		userRequest = new UserRequest();
		userRequest.setId(new UUID(0,10));
		userRequest.setName("test4");
		userRequest.setPassword("test14");
		userRequest.setEmail("test4@test.com");
		
//		phones = new ArrayList<PhoneEntity>();
//		phones.add(new PhoneEntity("1111", "11", "314"));
////	/	
//		Optional<UserEntity>  userEntity = Optional.ofNullable(new UserEntity(userRequest.getId(), "Test1", "test@test.com", "Test12", phones, LocalDateTime.now(), LocalDateTime.now(), true));

		when(userRepository.findByEmail(Matchers.any())).thenReturn(null);
		
		userResponse = userService.createUser(userRequest);
		
		verify(userRepository,times(1)).save(Matchers.any());
		
		
	}
	
	@Test
	public void updateUserTest() {
		
		userRequest = new UserRequest();
		userRequest.setId(new UUID(0,10));
		userRequest.setName("test4");
		userRequest.setPassword("test14");
		userRequest.setEmail("test4@test.com");
		
		phones = new ArrayList<PhoneEntity>();
		phones.add(new PhoneEntity("1111", "11", "314"));
		
		Optional<UserEntity>  userEntity = Optional.ofNullable(new UserEntity(userRequest.getId(), "Test1", "test@test.com", "Test12", phones, LocalDateTime.now(), LocalDateTime.now(), true));
		
		when(userRepository.findById(Matchers.any())).thenReturn(userEntity);
		
		//ResponseUserStatus responseStatus = new ResponseUserStatus("USR-200", "User TEST  updated!");
		
		ResponseUserStatus found = userService.updateUser(userRequest.getId(), userRequest);
		
		verify(userRepository,times(1)).save(Matchers.any());
		
	}
	
	
	@Test
	public void deleteUserTest() {
		
		userRequest = new UserRequest();
		userRequest.setId(new UUID(0,10));
		userRequest.setName("test4");
		userRequest.setPassword("test14");
		userRequest.setEmail("test4@test.com");
		
		phones = new ArrayList<PhoneEntity>();
		phones.add(new PhoneEntity("1111", "11", "314"));
		
		Optional<UserEntity>  userEntity = Optional.ofNullable(new UserEntity(userRequest.getId(), "Test1", "test@test.com", "Test12", phones, LocalDateTime.now(), LocalDateTime.now(), true));
		
		when(userRepository.findById(Matchers.any())).thenReturn(userEntity);
		
		userService.deleteUser(userRequest.getId());
		
		verify(userRepository,times(1)).deleteById(Matchers.any());
		
	}
	
	
	@Test()
	public void createUserErrorTest() {
		
		userRequest = new UserRequest();
		userRequest.setId(new UUID(0,10));
		userRequest.setName("test4");
		userRequest.setPassword("test14");
		userRequest.setEmail("test4@test.com");
		
		phones = new ArrayList<PhoneEntity>();
		phones.add(new PhoneEntity("1111", "11", "314"));
		
		UserEntity userEntity = new UserEntity(userRequest.getId(), "Test1", "test@test.com", "Test12", phones, LocalDateTime.now(), LocalDateTime.now(), true);
		
		
		when(userRepository.findByEmail(Matchers.any())).thenReturn(userEntity);
		
		assertThatThrownBy(() -> {userService.createUser(userRequest);}).isInstanceOf(UserException.class);
		
		verify(userRepository,times(0)).save(Matchers.any());
		
		
		
	}
	
	
	
}
