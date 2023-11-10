package com.ssafy.maytrip.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.maytrip.dto.request.CrewMappingRequest;
import com.ssafy.maytrip.dto.request.CrewRequest;
import com.ssafy.maytrip.dto.response.CrewResponse;
import com.ssafy.maytrip.service.CrewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/crews")
@RequiredArgsConstructor
public class CrewController {

	private final CrewService crewService;
	
	@PutMapping
	public ResponseEntity<CrewResponse> modify(@RequestBody CrewRequest crewRequest) {
		CrewResponse crew = crewService.modify(crewRequest);
		return ResponseEntity.ok(crew);
	}
	
	@GetMapping("/{crewId}")
	public ResponseEntity<CrewResponse> selectById(@PathVariable int crewId) {
		CrewResponse crew = crewService.selectById(crewId);
		return ResponseEntity.ok(crew);
	}
	
	@DeleteMapping("/{crewId}")
	public ResponseEntity<Void> deleteById(@PathVariable int crewId) {
		crewService.delete(crewId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/join")
	public ResponseEntity<Void> join(@RequestBody CrewMappingRequest mappingRequest) {
		crewService.join(mappingRequest);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/cancel")
	public ResponseEntity<Void> cancel(@RequestBody CrewMappingRequest mappingRequest) {
		crewService.cancel(mappingRequest);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
