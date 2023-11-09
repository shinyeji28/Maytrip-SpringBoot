package com.ssafy.maytrip.service;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.Board;
import com.ssafy.maytrip.domain.Gugun;
import com.ssafy.maytrip.domain.Sido;
import com.ssafy.maytrip.dto.BoardDto;
import com.ssafy.maytrip.exception.handler.IdNotFoundException;
import com.ssafy.maytrip.repository.BoardRepository;
import com.ssafy.maytrip.repository.GugunRepository;
import com.ssafy.maytrip.repository.SidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoarderService {
	
	private final BoardRepository boardRepository;
	private final SidoRepository sidoRepository;
	private final GugunRepository gugunRepository;

	public int regist(BoardDto boardDto) {
		Sido sido = sidoRepository.findBySidoCode(boardDto.getSidoCode())
				.orElseThrow(() -> new IdNotFoundException("sido code가 존재하지 않습니다."));
		Gugun gugun = gugunRepository.findBySidoCodeAndGugunCode(boardDto.getSidoCode(), boardDto.getGugunCode())
				.orElseThrow(() -> new IdNotFoundException("gugun code가 존재하지 않습니다."));
		
		Board board = Board.builder()
				.title(boardDto.getTitle())
				.content(boardDto.getContent())
				.startDate(boardDto.getStartDate())
				.endDate(boardDto.getEndDate())
				.headcount(boardDto.getHeadcount())
				.sido(sido)
				.gugun(gugun)
				.build();
		
		return boardRepository.save(board).getId();
	}
}
