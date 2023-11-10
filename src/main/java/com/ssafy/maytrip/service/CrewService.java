package com.ssafy.maytrip.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.maytrip.domain.Board;
import com.ssafy.maytrip.domain.Crew;
import com.ssafy.maytrip.domain.CrewMapping;
import com.ssafy.maytrip.domain.Member;
import com.ssafy.maytrip.dto.request.CrewMappingRequest;
import com.ssafy.maytrip.dto.request.CrewRequest;
import com.ssafy.maytrip.dto.response.CrewResponse;
import com.ssafy.maytrip.exception.IdNotFoundException;
import com.ssafy.maytrip.repository.BoardRepository;
import com.ssafy.maytrip.repository.CrewMappingRepository;
import com.ssafy.maytrip.repository.CrewRepository;
import com.ssafy.maytrip.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrewService {

	private final BoardRepository boardRepository;
	private final CrewRepository crewRepository;
	private final CrewMappingRepository crewMappingRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public CrewResponse modify(CrewRequest crewRequest) {
		Board board = boardRepository.findById(crewRequest.getId())
				.orElseThrow(() -> new IdNotFoundException("게시글을 찾을 수 없습니다."));
		Crew crew = Crew.builder()
				.id(crewRequest.getId())
				.crewName(crewRequest.getCrewName())
				.cost(crewRequest.getCost())
				.board(board)
				.build();
		crew = crewRepository.save(crew);
		return CrewResponse.from(crew);
	}

	public CrewResponse selectById(int crewId) {
		Crew crew = crewRepository.findById(crewId)
				.orElseThrow(() -> new IdNotFoundException("크루를 찾을 수 없습니다."));
		
		return CrewResponse.from(crew);
	}

	public void delete(int crewId) {
		Crew crew = crewRepository.findById(crewId)
				.orElseThrow(() -> new IdNotFoundException("크루를 찾을 수 없습니다."));
		
		crewRepository.delete(crew);
	}

	public void join(CrewMappingRequest mappingRequest) {
		Crew crew = crewRepository.findById(mappingRequest.getCrewId())
				.orElseThrow(() -> new IdNotFoundException("크루를 찾을 수 없습니다."));
		Member member = memberRepository.findById(mappingRequest.getMemberId())
				.orElseThrow(() -> new IdNotFoundException("회원을 찾을 수 없습니다."));
		CrewMapping crewMapping = CrewMapping.builder()
				.crew(crew)
				.member(member)
				.build();
		crewMappingRepository.save(crewMapping);
	}

	public void cancel(CrewMappingRequest mappingRequest) {
		CrewMapping crewMapping = crewMappingRepository.findByCrewIdAndMemberId(mappingRequest.getCrewId(), mappingRequest.getMemberId())
				.orElseThrow(() -> new IdNotFoundException("그룹 참여 정보가 없습니다."));
		
		crewMappingRepository.delete(crewMapping);
	}
	
}
