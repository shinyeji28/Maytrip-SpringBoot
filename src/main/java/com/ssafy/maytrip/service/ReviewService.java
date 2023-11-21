package com.ssafy.maytrip.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.Crew;
import com.ssafy.maytrip.domain.Review;
import com.ssafy.maytrip.dto.request.ReviewRequest;
import com.ssafy.maytrip.dto.response.ReviewResponse;
import com.ssafy.maytrip.exception.IdNotFoundException;
import com.ssafy.maytrip.repository.CrewRepository;
import com.ssafy.maytrip.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	
	private final ReviewRepository reviewRepository;
	private final CrewRepository crewRepository;
	
	@Transactional
	public void regist(ReviewRequest reviewRequest) {
		
		Review review = Review.builder()
					.title(reviewRequest.getTitle())
					.content(reviewRequest.getContent())
					.build();

		review = reviewRepository.save(review);
		
		Crew crew = crewRepository.findById(reviewRequest.getCrewId())
				.orElseThrow(()-> new IdNotFoundException("크루가 존재하지 않습니다."));
		
		crewRepository.updateReview(reviewRequest.getCrewId(), review.getId());
		
	}
	
	public List<ReviewResponse> getAll() {
		List<Review> reviews = reviewRepository.findAll();
		
		List<ReviewResponse> reviewResponse = new ArrayList<>();
		for(Review review : reviews ) {
			reviewResponse.add(ReviewResponse.from(review));
		}
		return reviewResponse;
	}
	
	public ReviewResponse getbyCrewId(int crewId) {
		Crew crew = Crew.builder().id(crewId).build();
		crew = crewRepository.findById(crew.getId())
			.orElseThrow(() -> new IdNotFoundException("크루가 존재하지 않습니다."));

		ReviewResponse reviewResponse = ReviewResponse.from(crew.getReview());
		return reviewResponse;
	}

	

}
