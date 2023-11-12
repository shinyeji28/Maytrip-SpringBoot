package com.ssafy.maytrip.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class AuthenticationFailedException extends RuntimeException{

	private final HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
	private final String message;
}
