package com.xdomain.user.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xdomain.user.model.ResponseUserStatus;
import com.xdomain.user.model.UserRequest;
import com.xdomain.user.model.UserResponse;
import com.xdomain.user.service.UserService;
import com.xdomain.user.utils.Constants;

@RestController
@Validated
public class UserController {

	
	@Autowired
	private UserService userService;
	
	
//	@Autowired
//	private ConversionService convService;
//	
	
	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponse> getUsers(){
		
		return userService.getUsers(); 
	}
	
	@GetMapping("/user/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse getUserById(@Valid @PathVariable(value = "id") UUID id){
		
		return userService.getUserById(id);
	}
	
		
	@PostMapping(value = "/user", consumes = "application/json" , produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse createUser(@RequestBody UserRequest user) {
		
		return userService.createUser(user);
	}
	
	@PutMapping(value = "/user/{id}" , consumes = "application/json",  produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseUserStatus updateUser(@PathVariable(value = "id")UUID id ,  @RequestBody UserRequest userRequest){
		
		 return userService.updateUser(id, userRequest);
	}
	
	
	@DeleteMapping(value = "/user/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseUserStatus deleteUser(@PathVariable(value = "id") UUID id) {
		
		return userService.deleteUser(id);
	
	}
	
	
}
