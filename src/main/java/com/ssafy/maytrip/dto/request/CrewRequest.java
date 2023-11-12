package com.ssafy.maytrip.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CrewRequest {

	private int id;
	
	private String crewName;
	
	private int cost;
}
