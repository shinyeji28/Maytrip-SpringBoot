package com.ssafy.maytrip.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.maytrip.dto.MemberDto;
import com.ssafy.maytrip.service.MemberService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/login")
	public ResponseEntity<MemberDto> login(@RequestBody MemberDto memberDto) {
		MemberDto member = memberService.login(memberDto);
		if(member != null) return ResponseEntity.ok(member);
		else return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
