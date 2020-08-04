package com.xdomain.user.model;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Validated
public class UserRequest {

	private UUID id;

	@NotBlank
	private String name;
	
	@Pattern(regexp = "[a-z0-9]+@[a-z0-9]+\\.[a-z0-9]+") 
	private String email;
	
	@Pattern(regexp = "[A-Z][a-z]+[0-9]{1,2}")
	private String password;
	private List<Phone> phones;
}
