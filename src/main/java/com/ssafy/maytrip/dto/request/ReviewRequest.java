package com.ssafy.maytrip.dto.request;

import com.ssafy.maytrip.domain.Review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {
	
	private int crewId;
	
//	private int id;
//	
//	private int like;

	private String title;
	
	private String content;
	

}
