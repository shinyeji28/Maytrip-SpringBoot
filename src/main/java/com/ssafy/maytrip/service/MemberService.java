package com.ssafy.maytrip.service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

import com.ssafy.maytrip.dto.response.MemberResponse;
import com.ssafy.maytrip.exception.AuthenticationFailedException;
import com.ssafy.maytrip.exception.IdNotFoundException;
import com.ssafy.maytrip.file.FileUpload;
import com.ssafy.maytrip.jwt.JWTUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.maytrip.domain.EmailCheck;
import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.domain.Member;
import com.ssafy.maytrip.dto.FileInfoDto;
import com.ssafy.maytrip.dto.request.EmailCheckRequest;
import com.ssafy.maytrip.dto.request.MemberRequest;
import com.ssafy.maytrip.repository.EmailCheckRepository;
import com.ssafy.maytrip.repository.FileInfoRepository;
import com.ssafy.maytrip.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final FileInfoRepository fileInfoRepository;
	private final EmailService emailService;
	private final EmailCheckRepository emailCheckRepository;

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
				.email(memberRequest.getEmail())
				.build();
		memberRepository.save(member);
	}

	public MemberResponse selectByMemberId(int memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new IdNotFoundException("회원 정보를 찾을 수 없습니다."));
		return MemberResponse.from(member);
	}

	public MemberResponse modify(MemberRequest memberRequest) {
		Member member = memberRepository.findById(memberRequest.getMemberId())
						.orElseThrow(() -> new IdNotFoundException("회원 정보를 찾을 수 없습니다."));
		if(!memberRequest.getProfileImg().isEmpty()) {
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


	public void delete(int memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new IdNotFoundException("회원 정보를 찾을 수 없습니다."));
		memberRepository.delete(member);
	}


	@Transactional
	public void emailAuthentication(String email) throws UnsupportedEncodingException, MessagingException {
		// 현재 회원
		Member member = memberRepository.findByEmail(email)
				.orElseThrow(() -> new IdNotFoundException("회원 정보를 찾을 수 없습니다."));
		
		Optional<EmailCheck> prevEmailCheck = emailCheckRepository.findByMemberId(member.getId());
		if(prevEmailCheck.isPresent()) {
			emailCheckRepository.delete(prevEmailCheck.get());
		}
		
		String code = emailService.sendEmail(member.getEmail());
		
		EmailCheck emailCheck = EmailCheck.builder()
				.code(code)
				.member(member)
				.build();
		emailCheckRepository.save(emailCheck);
	}

	public void matchCode(EmailCheckRequest request) {
		EmailCheck emailCheck = emailCheckRepository.findByMemberEmailAndCode(request.getEmail(), request.getCode())
				.orElseThrow(() -> new AuthenticationFailedException("인증번호가 일치하지 않습니다."));
	}


	public void changePassword(EmailCheckRequest emailCheckRequest) {
		Member member = memberRepository.findByEmail(emailCheckRequest.getEmail())
				.orElseThrow(() -> new IdNotFoundException("회원 정보를 찾을 수 없습니다."));
		member.changePassword(emailCheckRequest.getPassword());
		memberRepository.save(member);
	}
}
