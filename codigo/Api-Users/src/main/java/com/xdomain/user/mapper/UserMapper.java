package com.xdomain.user.mapper;

import java.util.List;
import java.util.Optional;

import com.xdomain.user.entities.UserEntity;
import com.xdomain.user.model.UserRequest;
import com.xdomain.user.model.UserResponse;

public interface UserMapper {

	UserEntity convertToEntity(UserRequest userRequest, UserEntity userEntity);
	UserEntity convertToEntity(UserRequest userRequest);
	UserResponse convertToDto(UserEntity user);
	List<UserResponse> convertEntityToListDto (List<UserEntity> source);
	UserResponse convertToDto(Optional<UserEntity> user);
}
