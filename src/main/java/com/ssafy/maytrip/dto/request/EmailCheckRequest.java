package com.ssafy.maytrip.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailCheckRequest {

	private String email;
	
	private String code;
	
	private String password;
}
