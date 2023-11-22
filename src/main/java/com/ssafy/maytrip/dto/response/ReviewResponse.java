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
	
	private int likes;
	
	private String title;
	
	private String content;
	
	private FileInfoResponse thumbnail;

	public static ReviewResponse from(Review review) {
		return ReviewResponse.builder()
				.id(review.getId())
				.likes(review.getLikes())
				.title(review.getTitle())
				.content(review.getContent())
				.build();
	}
}
