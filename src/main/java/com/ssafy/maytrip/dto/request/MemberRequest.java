package com.ssafy.maytrip.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequest {

	private int memberId;
	private String username;
	private String password;
	private String name;
}
