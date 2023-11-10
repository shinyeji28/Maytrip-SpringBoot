package com.ssafy.maytrip.dto.response;

import com.ssafy.maytrip.domain.Crew;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CrewResponse {

	private int id;
	
	private String crewName;
	
	private int cost;
	
	public static CrewResponse from(Crew crew) {
		return CrewResponse.builder()
				.id(crew.getId())
				.cost(crew.getCost())
				.crewName(crew.getCrewName())
				.build();
	}
	
}
