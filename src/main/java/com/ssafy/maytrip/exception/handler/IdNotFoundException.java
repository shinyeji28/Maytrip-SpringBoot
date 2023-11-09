package com.ssafy.maytrip.exception.handler;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class IdNotFoundException extends RuntimeException{

	private final HttpStatus status = HttpStatus.NOT_FOUND;
	private final String message;
}
