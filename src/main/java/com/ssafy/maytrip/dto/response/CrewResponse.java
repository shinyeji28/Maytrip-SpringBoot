package com.ssafy.maytrip.dto.response;


import com.ssafy.maytrip.domain.Board;
import com.ssafy.maytrip.domain.Crew;

import com.ssafy.maytrip.domain.Member;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Builder
public class CrewResponse {

	private int id;
	
	private String crewName;
		
	List<MemberResponse> members;
	
	private BoardResponse board;
	
	private Long count;
	
	public static CrewResponse from(Crew crew) {
		return CrewResponse.builder()
				.id(crew.getId())
				.crewName(crew.getCrewName())
				.build();
	}

	public static CrewResponse from(Crew crew, Board board, List<Member> members) {
		return CrewResponse.builder()
				.id(crew.getId())
				.crewName(crew.getCrewName())
				.board(BoardResponse.from(board))
				.members(members.stream().map(MemberResponse::from).collect(Collectors.toList()))
				.build();
	}

	public static CrewResponse from(Crew crew, Board board, Long count) {
		return CrewResponse.builder()
				.id(crew.getId())
				.crewName(crew.getCrewName())
				.board(BoardResponse.from(board))
				.count(count)
				.build();
	}
}
