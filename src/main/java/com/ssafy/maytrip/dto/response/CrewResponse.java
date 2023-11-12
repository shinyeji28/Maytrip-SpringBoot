package com.ssafy.maytrip.dto.response;

import com.ssafy.maytrip.domain.Crew;

import com.ssafy.maytrip.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CrewResponse {

	private int id;
	
	private String crewName;
	
	private int cost;

	List<MemberResponse> members;
	
	public static CrewResponse from(Crew crew) {
		return CrewResponse.builder()
				.id(crew.getId())
				.cost(crew.getCost())
				.crewName(crew.getCrewName())
				.build();
	}

	public static CrewResponse from(Crew crew, List<Member> members) {
		return CrewResponse.builder()
				.id(crew.getId())
				.cost(crew.getCost())
				.crewName(crew.getCrewName())
				.members(members.stream().map(MemberResponse::from).collect(Collectors.toList()))
				.build();
	}
}
