package com.ssafy.maytrip.controller;

import com.ssafy.maytrip.dto.response.MemberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.maytrip.dto.request.MemberRequest;
import com.ssafy.maytrip.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<MemberResponse> modify(@ModelAttribute MemberRequest memberRequest) {
		MemberResponse member = memberService.modify(memberRequest);
		return ResponseEntity.ok(member);
	}
	
	@DeleteMapping("/user/{memberId}")
	public ResponseEntity<Void> delete(@PathVariable int memberId) {
		memberService.delete(memberId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
