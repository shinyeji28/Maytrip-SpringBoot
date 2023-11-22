package com.ssafy.maytrip.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.dto.FileInfoDto;
import com.ssafy.maytrip.dto.request.ReviewRequest;
import com.ssafy.maytrip.dto.response.ReviewDetailResponse;
import com.ssafy.maytrip.dto.response.ReviewResponse;
import com.ssafy.maytrip.file.FileUpload;
import com.ssafy.maytrip.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewContoller {
	private final ReviewService reviewService;
	
	@PostMapping
	public void regist(@ModelAttribute ReviewRequest reviewRequest,
			@RequestParam(value="images", required = false) List<MultipartFile> files,
			@RequestParam(value="thumb", required = false) MultipartFile thumbnail
			){
		FileInfoDto thumbInfoDto = null;
		if(thumbnail.getSize()!=0) {
			thumbInfoDto = FileUpload.makeFileSource(thumbnail);
		}
		
		List<FileInfoDto> fileInfoDtos = new ArrayList<>();
		if(files != null) {
			for(MultipartFile file : files) {
				fileInfoDtos.add(FileUpload.makeFileSource(file));
			}
			
		}
		reviewRequest.setFiles(fileInfoDtos);
		reviewRequest.setThumbnail(thumbInfoDto);
		reviewService.regist(reviewRequest);
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<ReviewResponse> ReviewResponse = reviewService.getAll();
		return ResponseEntity.ok(ReviewResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getByReviewId(@PathVariable(value="id") int reviewId){
		ReviewDetailResponse reviewResponse = reviewService.getByReviewId(reviewId);
		return ResponseEntity.ok(reviewResponse);
	}
}
