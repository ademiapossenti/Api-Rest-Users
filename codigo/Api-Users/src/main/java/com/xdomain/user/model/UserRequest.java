package com.xdomain.user.model;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Pattern;

import com.xdomain.user.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

	private UUID id;
	private String name;
	
	@Pattern(regexp = Constants.USER_EMAIL_REGEX)
	private String email;
	
	@Pattern(regexp = "^[A-Z]{1}[a-z]+/d{1-2}")
	private String password;
	private List<Phone> phones;
}
