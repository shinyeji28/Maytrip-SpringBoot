package com.ssafy.maytrip.controller;

import com.ssafy.maytrip.domain.Crew;
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

import java.util.List;

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

	@GetMapping("/all/{memberId}")
	public ResponseEntity<List<CrewResponse>> selectAllByMemberId(@PathVariable int memberId) {
		List<CrewResponse> crews = crewService.selectAllByMemberId(memberId);
		return ResponseEntity.ok(crews);
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
