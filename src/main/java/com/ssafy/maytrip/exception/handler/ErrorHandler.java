package com.ssafy.maytrip.exception.handler;

import com.ssafy.maytrip.exception.AuthenticationFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.maytrip.exception.IdNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorMessage> idNotFoundException(IdNotFoundException e) {
		return ResponseEntity.status(e.getStatus()).body(ErrorMessage.from(e.getStatus(), e.getMessage()));
	}

	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<ErrorMessage> authenticationFailedException(AuthenticationFailedException e) {
		return ResponseEntity.status(e.getStatus()).body(ErrorMessage.from(e.getStatus(), e.getMessage()));
	}
	
}
