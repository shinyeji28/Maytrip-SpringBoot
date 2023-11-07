package com.ssafy.maytrip.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.Member;
import com.ssafy.maytrip.dto.MemberDto;
import com.ssafy.maytrip.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberDto login(MemberDto memberDto) {
		Optional<Member> member = memberRepository.findByUsernameAndPassword(memberDto.getUsername(), memberDto.getPassword());
		MemberDto dto = null;
		if(member.isPresent()) {
			dto = MemberDto.builder()
					.username(member.get().getUsername())
					.password(member.get().getPassword())
					.build();
		}
		return dto;
	}
	
	
	
}
