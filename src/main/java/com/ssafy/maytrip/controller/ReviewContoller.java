package com.ssafy.maytrip.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.maytrip.dto.request.ReviewRequest;
import com.ssafy.maytrip.dto.response.ReviewResponse;
import com.ssafy.maytrip.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewContoller {
	private final ReviewService reviewService;
	
	@PostMapping
	public void regist(@ModelAttribute ReviewRequest reviewRequest){
		System.out.println(reviewRequest);
		reviewService.regist(reviewRequest);
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<ReviewResponse> ReviewResponse = reviewService.getAll();
		return ResponseEntity.ok(ReviewResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getbyCrewId(@PathVariable(value="id") int crewId){
		ReviewResponse reviewResponse = reviewService.getbyCrewId(crewId);
		return ResponseEntity.ok(reviewResponse);
	}
}
