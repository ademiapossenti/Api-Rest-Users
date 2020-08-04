package com.xdomain.user.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdomain.user.entities.UserEntity;
import com.xdomain.user.exception.UserException;
import com.xdomain.user.mapper.UserMapper;
import com.xdomain.user.model.ResponseUserStatus;
import com.xdomain.user.model.UserRequest;
import com.xdomain.user.model.UserResponse;
import com.xdomain.user.repository.UserRepository;
import com.xdomain.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;


	@Override
	public List<UserResponse> getUsers() {
		
		List<UserEntity> user = userRepository.findAll();

		return userMapper.convertEntityToListDto(user);
	}

	@Override
	public UserResponse getUserById(UUID id) {


		UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND.value(),
				String.format("The user with id %s not found", id), HttpStatus.NOT_FOUND));

		UserResponse userResponse = userMapper.convertToDto(user);

		return userResponse;
	}

	@Override
	@Transactional
	public UserResponse createUser(UserRequest userRequest) {


		UserEntity user = userMapper.convertToEntity(userRequest);

		if (userRepository.findByEmail(user.getEmail()) != null) {

			throw new UserException(HttpStatus.PRECONDITION_FAILED.value(),
					String.format("An user with email %s already exists", user.getEmail()),
					HttpStatus.PRECONDITION_FAILED);
		}

		user.setUserCreated(LocalDateTime.now());
		user.setUserModified(LocalDateTime.now());
		user.setIsActive(true);

		userRepository.save(user);

		UserResponse userResponse = userMapper.convertToDto(user);

		return userResponse;
	}

	@Override
	public ResponseUserStatus updateUser(UUID id, UserRequest userRequest) {


		UserEntity userEntity = userRepository.findById(id)
				.orElseThrow(() -> new UserException(HttpStatus.PRECONDITION_FAILED.value(),
						String.format("User with id %s not found", id), HttpStatus.PRECONDITION_FAILED));

		userRequest.setId(userEntity.getId());
		userEntity.setUserModified(LocalDateTime.now());

		userEntity = userMapper.convertToEntity(userRequest, userEntity);

		userRepository.save(userEntity);

		return new ResponseUserStatus(HttpStatus.OK.value(), "Resource was updated sucessfully");

	}

	@Override
	public ResponseUserStatus deleteUser(UUID id) {

		UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserException(HttpStatus.PRECONDITION_FAILED.value(),
				String.format("User with id %s not found", id), HttpStatus.PRECONDITION_FAILED));


		userRepository.deleteById(user.getId());

		return new ResponseUserStatus(HttpStatus.OK.value(), "Resource was deleted sucessfully");

	}

}
