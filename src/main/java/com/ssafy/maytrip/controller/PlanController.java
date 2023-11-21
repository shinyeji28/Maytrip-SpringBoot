package com.ssafy.maytrip.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.ssafy.maytrip.dto.request.DetailRequest;
import com.ssafy.maytrip.dto.request.PlanRequest;
import com.ssafy.maytrip.dto.response.DetailResponse;
import com.ssafy.maytrip.dto.response.PlanResponse;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<?> selctPlan(@PathVariable(value="id") int crewId){
		PlanResponse planResponse = planService.selectPlan(crewId);
		return ResponseEntity.ok(planResponse);
	}

	@PutMapping
	public ResponseEntity<Void> updatePlan(@RequestBody PlanRequest planRequest){
		planService.updatePlan(planRequest);
		return ResponseEntity.status(HttpStatus.OK).build();

	}
	
	@PostMapping("/detail")
	public ResponseEntity<DetailResponse> insertDetail(@RequestBody DetailRequest detailRequest) {
		DetailResponse detail = planService.insertDetail(detailRequest);
		return ResponseEntity.ok(detail);
	}
	
	@DeleteMapping("/detail/{detailId}")
	public ResponseEntity<Void> deleteDetail(@PathVariable int detailId) {
		planService.deleteDetail(detailId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
