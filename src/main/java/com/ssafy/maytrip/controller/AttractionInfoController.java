package com.ssafy.maytrip.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.maytrip.domain.AttractionDescription;
import com.ssafy.maytrip.dto.AttractionDetailDto;
import com.ssafy.maytrip.dto.AttractionInfoDto;
import com.ssafy.maytrip.dto.response.AttractionDescriptionResponse;
import com.ssafy.maytrip.service.AttractionDescriptionService;
import com.ssafy.maytrip.service.AttractionInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attraction")
public class AttractionInfoController {
	
	private final AttractionInfoService attractionInfoService;
	private final AttractionDescriptionService attractionDescriptionService;

	@GetMapping
	public ResponseEntity<?> getAllAttraction(
			@RequestParam(value="lat") double lat,
			@RequestParam(value="lon") double lon,
			@RequestParam(value="nkm") double radiusInKm
			){
		List<AttractionInfoDto> attractions = attractionInfoService.getAllAttraction(lat, lon, radiusInKm);
		if(attractions != null) {
			return ResponseEntity.ok(attractions);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getAttractionBySidoGugun(
				@RequestParam(value="sido") int sidoCode,
				@RequestParam(value="gugun") int gugunCode
			){
		List<AttractionInfoDto> attractions = attractionInfoService.getAttractionBySidoGugun(sidoCode, gugunCode);
		if(attractions != null) {
			return ResponseEntity.ok(attractions);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/cate")
	public ResponseEntity<?> getAttractionByCategory(
				@RequestParam String cat1,
				@RequestParam String cat2,
				@RequestParam String cat3
			){
		List<AttractionInfoDto> attractions = attractionInfoService.getAttractionByCategory(cat1,cat2,cat3);
		if(attractions != null) {
			return ResponseEntity.ok(attractions);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/keyword")
	public ResponseEntity<?> getAtrractionByKeyword(
				@RequestParam String key,
				@RequestParam String word
			){
		List<AttractionInfoDto> attractions = attractionInfoService.getAttractionByWord(key, word);
		return ResponseEntity.ok(attractions);
	}
	
	@GetMapping("/detail/{contentId}")
	public ResponseEntity<?> getDescriptionByContentId(@PathVariable(value="contentId") int contentId){
		AttractionDescriptionResponse description = attractionDescriptionService.getOverviewByContentId(contentId);
		return ResponseEntity.ok(description);

	}
	
}
