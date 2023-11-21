package com.ssafy.maytrip.service;

import java.util.Optional;

import com.ssafy.maytrip.dto.response.MemberResponse;
import com.ssafy.maytrip.exception.AuthenticationFailedException;
import com.ssafy.maytrip.exception.IdNotFoundException;
import com.ssafy.maytrip.file.FileUpload;
import com.ssafy.maytrip.jwt.JWTUtil;
import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.domain.Member;
import com.ssafy.maytrip.dto.FileInfoDto;
import com.ssafy.maytrip.dto.request.MemberRequest;
import com.ssafy.maytrip.repository.FileInfoRepository;
import com.ssafy.maytrip.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final FileInfoRepository fileInfoRepository;

	public MemberResponse login(MemberRequest memberRequest) {
		Member member = memberRepository.findByUsernameAndPassword(memberRequest.getUsername(), memberRequest.getPassword())
				.orElseThrow(() -> new AuthenticationFailedException("로그인에 실패했습니다."));
		return MemberResponse.from(JWTUtil.generateToken(member));
	}


	public void signup(MemberRequest memberRequest) {
		Member member = Member.builder()
				.username(memberRequest.getUsername())
				.password(memberRequest.getPassword())
				.name(memberRequest.getName())
				.build();
		memberRepository.save(member);
	}

	public MemberResponse selectByMemberId(int memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new IdNotFoundException("회원 정보를 찾을 수 없습니다."));
		return MemberResponse.from(member);
	}

	public MemberResponse modify(MemberRequest memberRequest) {
		System.out.println("회원 정보 : " + memberRequest);
		Member member = memberRepository.findById(memberRequest.getMemberId())
						.orElseThrow(() -> new IdNotFoundException("회원 정보를 찾을 수 없습니다."));
		if(memberRequest.getProfileImg() != null) {
			FileInfoDto profileImg = FileUpload.makeFileSource(memberRequest.getProfileImg());
			FileInfo fileInfo = fileInfoRepository.save(
					FileInfo.builder()
					.originalFile(profileImg.getOriginalFile())
					.saveFile(profileImg.getSaveFile())
					.saveFolder(profileImg.getSaveFolder())
					.build());			
			member.updateProfileImg(fileInfo);
		}
		member.update(memberRequest);			
		member = memberRepository.save(member);
		return MemberResponse.from(member);
	}
}
