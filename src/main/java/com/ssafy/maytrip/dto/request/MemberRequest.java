package com.ssafy.maytrip.dto.request;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@NoArgsConstructor
@Setter
@ToString
public class MemberRequest {

	// 자동 생성되는 키값
	private int memberId;
	// 유저 ID
	private String username;
	private String password;
	// 유저 이름
	private String name;
	
	private MultipartFile profileImg;
}
