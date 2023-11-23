package com.ssafy.maytrip.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.domain.Review;
import com.ssafy.maytrip.dto.request.ReviewRequest;
import com.ssafy.maytrip.file.FileUpload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {
		
	private int id;
	
	private int likes;
	
	private String title;
	
	private String content;

	private int views;
	
	private FileInfoResponse thumbnailInfo;
	
	private List<FileInfoResponse> images;

	public static ReviewResponse from(Review review) {
		
		return ReviewResponse.builder()
				.id(review.getId())
				.likes(review.getLikes())
				.title(review.getTitle())
				.content(review.getContent())
				.views(review.getViews())
				.thumbnailInfo(review.getFileInfo() == null ? null : FileUpload.toImageUrl(review.getFileInfo()))
				.build();
	}
}
