package com.xdomain.user.model;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class Error {

	private String code;
	private String description;
	
	
	
}
