package com.xdomain.user.controller;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.TypeConstraintException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xdomain.user.exception.UserException;
import com.xdomain.user.model.Error;
import com.xdomain.user.utils.Constants;

@ControllerAdvice
public class UserExceptionHandler {

	
	@Autowired
	Error error;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> handlerException(Exception e) {

		error.setCode(Constants.USER_PREFIX_ERROR + "-" + HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setDescription(e.getMessage());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(TypeConstraintException.class)
	public ResponseEntity<Error> handlerTypeConstraintException(TypeConstraintException e) {

		error.setCode(Constants.USER_PREFIX_ERROR + "-" + e.getErrorCode());
		error.setDescription(e.getMessage());

		return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);

	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<Error> handlerUserException(UserException e) {

		error.setCode(Constants.USER_PREFIX_ERROR + "-" +e.getCode());
		error.setDescription(e.getDescription());

		return new ResponseEntity<>(error, e.getHttpsStatus());

	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Error> handlerUserException(EntityNotFoundException e) {

		error.setCode(Constants.USER_PREFIX_ERROR + "-" + HttpStatus.NOT_FOUND.value());
		error.setDescription(e.getMessage());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Error> handlerUserException(HttpRequestMethodNotSupportedException e) {

		error.setCode(Constants.USER_PREFIX_ERROR + "-" + HttpStatus.METHOD_NOT_ALLOWED.value());
		error.setDescription(e.getMessage());

		return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);

	}
	
		
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<Error> handlerUserException(JsonProcessingException e) {

		error.setCode(Constants.USER_PREFIX_ERROR + "-" + HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setDescription(e.getMessage());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
}
