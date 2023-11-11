package com.ssafy.maytrip.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CrewMappingRequest {

	private int id;
	
	private int memberId;
	
	private int crewId;
	
}
