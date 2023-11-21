package com.ssafy.maytrip.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.maytrip.domain.Review;
import com.ssafy.maytrip.dto.request.ReviewRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {
		
	private int id;
	
	private int like;
	
	private String title;
	
	private String content;

	public static ReviewResponse from(Review review) {
		return ReviewResponse.builder()
				.id(review.getId())
				.like(review.getId())
				.title(review.getTitle())
				.content(review.getContent())
				.build();
	}
}
