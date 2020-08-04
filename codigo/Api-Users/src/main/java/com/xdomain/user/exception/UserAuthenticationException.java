package com.xdomain.user.exception;

import org.springframework.http.HttpStatus;

public class UserAuthenticationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 703659790474793024L;
	
	private int code;
	private String description;
	private HttpStatus httpsStatus;
	
	public UserAuthenticationException(String message, Throwable cause) {
		super(message, cause);
		
	}
	public UserAuthenticationException(String message, int code, String description) {
		super(message);
		this.code = code;
		this.description = description;
		
	}
	
	public UserAuthenticationException(String message) {
		super(message);
	}
	
	
	
	public UserAuthenticationException(Throwable cause) {
		super(cause);
		
	}

}
