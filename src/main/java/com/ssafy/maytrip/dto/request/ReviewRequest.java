package com.ssafy.maytrip.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewRequest {
	
	private Integer crewId;
	
//	private int id;
//	
//	private int like;

	private String title;
	
	private String content;
	

}
