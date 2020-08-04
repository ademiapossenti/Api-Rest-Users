package com.xdomain.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class UserException extends RuntimeException{

	private static final long serialVersionUID = 2265384650860171537L;

	private int code;
	private String description;
	private HttpStatus httpsStatus;
	
	public UserException(String message, Throwable cause) {
		super(message, cause);
		
	}
	public UserException(String message, int code, String description) {
		super(message);
		this.code = code;
		this.description = description;
		
	}
	
	public UserException(String message, int code, String description, HttpStatus httpStatus) {
		super(message);
		this.code = code;
		this.description = description;
		this.httpsStatus = httpStatus;
		
		
	}
	
	
	public UserException(Throwable cause) {
		super(cause);
		
	}
	
	
	
}
