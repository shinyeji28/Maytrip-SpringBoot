package com.ssafy.maytrip.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.maytrip.dto.AttractionInfoDto;
import com.ssafy.maytrip.service.AttractionInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attraction")
public class AttractionInfoController {
	
	private final AttractionInfoService attractionInfoService;
	
	@GetMapping
	public ResponseEntity<?> getAllAttraction(){
		List<AttractionInfoDto> attractions = attractionInfoService.getAllAttraction();
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
}
