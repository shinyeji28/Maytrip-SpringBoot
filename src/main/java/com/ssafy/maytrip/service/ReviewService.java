package com.ssafy.maytrip.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.Crew;
import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.domain.Review;
import com.ssafy.maytrip.dto.FileInfoDto;
import com.ssafy.maytrip.dto.request.ReviewRequest;
import com.ssafy.maytrip.dto.response.PlanResponse;
import com.ssafy.maytrip.dto.response.ReviewDetailResponse;
import com.ssafy.maytrip.dto.response.ReviewResponse;
import com.ssafy.maytrip.exception.IdNotFoundException;
import com.ssafy.maytrip.repository.CrewRepository;
import com.ssafy.maytrip.repository.FileInfoRepository;
import com.ssafy.maytrip.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	
	private final ReviewRepository reviewRepository;
	private final CrewRepository crewRepository;
	private final FileInfoRepository fileInfoRepository;
	private final PlanService planService;
	
	@Transactional
	public void regist(ReviewRequest reviewRequest) {
		Crew crew = crewRepository.findById(reviewRequest.getCrewId())
				.orElseThrow(()-> new IdNotFoundException("크루가 존재하지 않습니다."));
		
		// 썸네일 저장
		FileInfo thumbFileInfo = FileInfo.builder()
				.saveFolder(reviewRequest.getThumbnail().getSaveFolder())
				.originalFile(reviewRequest.getThumbnail().getOriginalFile())
				.saveFile(reviewRequest.getThumbnail().getSaveFile())
				.build();
		
		
		// 리뷰 사진 저장
		for(FileInfoDto fileInfoDto : reviewRequest.getFiles()) {
			FileInfo fileInfo = FileInfo.builder()
					.saveFolder(fileInfoDto.getSaveFolder())
					.originalFile(fileInfoDto.getOriginalFile())
					.saveFile(fileInfoDto.getSaveFile())
					.crew(crew)
					.build();
			
			fileInfo = fileInfoRepository.save(fileInfo);
			
		}
		
		// 리뷰 내용 저장
		Review review = Review.builder()
					.title(reviewRequest.getTitle())
					.content(reviewRequest.getContent())
					.fileInfo(thumbFileInfo)
					.build();

		crew.setReview(review);
		
		crew = crewRepository.save(crew);
	}
	
	public List<ReviewResponse> getAll() {
		
		List<Review> reviews = reviewRepository.findAll();
		
		List<ReviewResponse> reviewResponse = new ArrayList<>();
		for(Review review : reviews ) {
			reviewResponse.add(ReviewResponse.from(review));
		}
		return reviewResponse;
	}
	
	public ReviewDetailResponse getByReviewId(int reviewId) {
		Review review = Review.builder().id(reviewId).build();
		// 리뷰 정보 얻기
		review = reviewRepository.findById(review.getId())
				.orElseThrow(() -> new IdNotFoundException("리뷰가 존재하지 않습니다."));
		
		ReviewResponse reviewResponse = ReviewResponse.from(review);
		
		Crew crew = crewRepository.findByReviewId(review.getId());
		
		// 플랜 정보 얻기
		PlanResponse planResponse = planService.selectPlan(crew.getId()); 
						
		return new ReviewDetailResponse(planResponse, reviewResponse);
	}
}
