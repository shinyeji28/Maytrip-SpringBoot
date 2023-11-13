package com.ssafy.maytrip.controller;

import com.ssafy.maytrip.dto.response.MemberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.maytrip.dto.request.MemberRequest;
import com.ssafy.maytrip.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/login")
	public ResponseEntity<MemberResponse> login(@RequestBody MemberRequest memberRequest) {
		MemberResponse member = memberService.login(memberRequest);
		return ResponseEntity.ok(member);
	}

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestBody MemberRequest memberRequest) {
		memberService.signup(memberRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/user/{memberId}")
	public ResponseEntity<MemberResponse> selectByMemberId(@PathVariable int memberId) {
		MemberResponse member = memberService.selectByMemberId(memberId);
		return ResponseEntity.ok(member);
	}

	@PutMapping("/user")
	public ResponseEntity<MemberResponse> modify(@RequestBody MemberRequest memberRequest) {
		MemberResponse member = memberService.modify(memberRequest);
		return ResponseEntity.ok(member);
	}

}
