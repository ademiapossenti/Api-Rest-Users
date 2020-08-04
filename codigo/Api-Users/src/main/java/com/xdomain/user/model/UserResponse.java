package com.xdomain.user.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {

	
	private UUID id;
	private String name;
	private String email;
	private String password;
	private List<Phone> phones;
	private LocalDateTime userCreated;
	private LocalDateTime userModified;
	private boolean isActive;
}
