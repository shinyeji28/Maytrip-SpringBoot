package com.ssafy.maytrip.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

	private int memberId;
	private String username;
	private String password;
	private String name;
}
