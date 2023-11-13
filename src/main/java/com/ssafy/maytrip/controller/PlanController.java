package com.ssafy.maytrip.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.ssafy.maytrip.dto.request.PlanRequest;
import com.ssafy.maytrip.service.PlanService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanController {
	
	private final PlanService planService;
	
	@PostMapping
	public ResponseEntity<Void> registPlan(@RequestBody PlanRequest planRequest){		
		planService.insertTravelDay(planRequest);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
