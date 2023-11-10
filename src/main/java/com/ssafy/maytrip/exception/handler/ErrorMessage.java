package com.ssafy.maytrip.exception.handler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorMessage {

	private HttpStatus status;
	private String message;
	
	public static ErrorMessage from(HttpStatus status, String message) {
		return ErrorMessage.builder()
				.status(status)
				.message(message)
				.build();
	}
}
