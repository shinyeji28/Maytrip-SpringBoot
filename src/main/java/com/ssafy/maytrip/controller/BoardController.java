package com.ssafy.maytrip.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.maytrip.dto.BoardDto;
import com.ssafy.maytrip.service.BoarderService;

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
	
	@GetMapping
	public ResponseEntity<List<BoardDto>> selectAll() {
		List<BoardDto> list = boardService.selectAll();
		return ResponseEntity.ok(list);
	}
}
