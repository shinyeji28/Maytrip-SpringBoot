package com.ssafy.maytrip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.maytrip.dto.GugunDto;
import com.ssafy.maytrip.dto.SidoDto;
import com.ssafy.maytrip.service.GugunService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gugun")
public class GugunController {
	private final GugunService gugunService;
	
	@GetMapping
	public ResponseEntity<?> getAllSido(){
		List<GugunDto> gugun = gugunService.getAllGugun();
		if(gugun != null ) {
			return ResponseEntity.ok(gugun);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/{sidoCode}")
	public ResponseEntity<?> getGugunBySidoCode(@PathVariable int sidoCode){
		List<GugunDto> gugun = gugunService.getGugunBySidoCode(sidoCode);
		if(gugun != null) {
			return ResponseEntity.ok(gugun);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
