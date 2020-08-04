package com.xdomain.user.service;

import java.util.List;
import java.util.UUID;

import com.xdomain.user.model.ResponseUserStatus;
import com.xdomain.user.model.UserRequest;
import com.xdomain.user.model.UserResponse;


public interface UserService {

	List<UserResponse> getUsers();
	UserResponse getUserById(UUID id);
	UserResponse createUser(UserRequest userRequest);
	ResponseUserStatus updateUser(UUID id, UserRequest userRequest);
	ResponseUserStatus deleteUser(UUID id);
}	
