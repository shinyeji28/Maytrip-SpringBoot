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

	// 자동 생성되는 키값
	private int memberId;
	// 유저 ID
	private String username;
	private String password;
	// 유저 이름
	private String name;
}
