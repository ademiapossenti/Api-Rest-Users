package com.xdomain.user.mapper.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

import com.xdomain.user.entities.UserEntity;
import com.xdomain.user.mapper.UserMapper;
import com.xdomain.user.model.UserRequest;
import com.xdomain.user.model.UserResponse;


@Component
public class UserMapperImpl implements UserMapper{

	
	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public UserEntity convertToEntity(UserRequest userRequest, UserEntity userEntity) {
		configureModel();
		
		UserEntity user = modelMapper.map(userRequest, UserEntity.class);
		
		user.setUserCreated(userEntity.getUserCreated());
		user.setUserModified(userEntity.getUserModified());
		user.setIsActive(userEntity.getIsActive());
		
		return user;
		
	}
	
	
	@Override
	public UserEntity convertToEntity(UserRequest userRequest) {
		configureModel();
		
		UserEntity user = modelMapper.map(userRequest, UserEntity.class);
		
		return user;
		
	}
	
	@Override
	public List<UserResponse> convertEntityToListDto (List<UserEntity> source){
		configureModel();
		List<UserResponse> response = source.stream().map(x -> modelMapper.map(x, UserResponse.class)).collect(Collectors.toList());
		
		return response;
				
	}
	
	
	@Override
	public UserResponse convertToDto(UserEntity user) {
		configureModel();
		
		UserResponse userResponse = modelMapper.map(user, UserResponse.class);
		
		return userResponse;
	}
	
	@Override
	public UserResponse convertToDto(Optional<UserEntity> user) {
	
		UserResponse userResponse = modelMapper.map(user, UserResponse.class);
		
		return userResponse;
	}
	
	
	private void configureModel() {
		modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
	}




	



	
	
}
