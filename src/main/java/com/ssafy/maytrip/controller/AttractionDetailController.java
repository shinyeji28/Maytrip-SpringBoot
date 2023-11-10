package com.ssafy.maytrip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.maytrip.dto.AttractionDetailDto;
import com.ssafy.maytrip.service.AttractionDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attraction")
public class AttractionDetailController {
	
	private AttractionDetailService attractionDetailService;

	@GetMapping("/cate")
	public ResponseEntity<?> getAttractionBySidoGugun(
				@RequestParam int cat1,
				@RequestParam int cat2,
				@RequestParam int cat3
			){
		List<AttractionDetailDto> attractions = attractionDetailService.getAttractionByContentId(cat1,cat2,cat3);
		if(attractions != null) {
			return ResponseEntity.ok(attractions);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
