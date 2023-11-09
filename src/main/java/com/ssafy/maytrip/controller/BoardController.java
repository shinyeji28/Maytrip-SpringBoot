package com.ssafy.maytrip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.maytrip.dto.BoardDto;
import com.ssafy.maytrip.service.BoarderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

	private final BoarderService boardService;
	
	@PostMapping
	public ResponseEntity<Integer> regist(@RequestBody BoardDto boardDto) {
		int savedId = boardService.regist(boardDto);
		return ResponseEntity.ok(savedId);
	}
}
