package com.ssafy.maytrip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.maytrip.dto.SidoDto;
import com.ssafy.maytrip.service.SidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sido")
public class SidoController {
	private final SidoService sidoService;
	
	@GetMapping
	public ResponseEntity<?> getAllSido(){
		List<SidoDto> sido = sidoService.getAllSido();
		System.out.println(sido);
		if(sido != null ) {
			return ResponseEntity.ok(sido);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
