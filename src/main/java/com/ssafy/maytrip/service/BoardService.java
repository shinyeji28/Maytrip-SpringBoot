package com.ssafy.maytrip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.Board;
import com.ssafy.maytrip.domain.Gugun;
import com.ssafy.maytrip.domain.GugunId;
import com.ssafy.maytrip.domain.Sido;
import com.ssafy.maytrip.dto.request.BoardRequest;
import com.ssafy.maytrip.dto.response.BoardResponse;
import com.ssafy.maytrip.exception.handler.IdNotFoundException;
import com.ssafy.maytrip.repository.BoardRepository;
import com.ssafy.maytrip.repository.GugunRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final GugunRepository gugunRepository;

	public int regist(BoardRequest boardDto) {
		Gugun gugun = gugunRepository.findByGugunIdSidoSidoCodeAndGugunIdGugunCode(boardDto.getSidoCode(), boardDto.getGugunCode())
				.orElseThrow(() -> new IdNotFoundException("gugun code가 존재하지 않습니다."));
		
		Board board = Board.builder()
				.title(boardDto.getTitle())
				.content(boardDto.getContent())
				.startDate(boardDto.getStartDate())
				.endDate(boardDto.getEndDate())
				.headcount(boardDto.getHeadcount())
				.gugun(gugun)
				.build();
		
		return boardRepository.save(board).getId();
	}

	public List<BoardResponse> selectAll() {
		List<Board> boards = boardRepository.findAll();
		List<BoardResponse> boardDtos = new ArrayList<BoardResponse>();
		for(Board board : boards) {
			// Board 객체로 BoardResponse 생성
			boardDtos.add(BoardResponse.from(board));
		}
		return boardDtos;
	}

	public BoardResponse selectByBoardId(int boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new IdNotFoundException("게시글을 찾을 수 없습니다."));
		board.updateViews();
		board = boardRepository.save(board);
		return BoardResponse.from(board);
	}

	public BoardResponse modify(BoardRequest boardDto) {
		Gugun gugun = gugunRepository.findByGugunIdSidoSidoCodeAndGugunIdGugunCode(boardDto.getSidoCode(), boardDto.getGugunCode())
				.orElseThrow(() -> new IdNotFoundException("gugun code가 존재하지 않습니다."));
		
		Board board = Board.builder()
				.id(boardDto.getId())
				.title(boardDto.getTitle())
				.content(boardDto.getContent())
				.startDate(boardDto.getStartDate())
				.endDate(boardDto.getEndDate())
				.headcount(boardDto.getHeadcount())
				.gugun(gugun)
				.build();
		
		boardRepository.save(board);
		return BoardResponse.from(board);
	}

	public void delete(int boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new IdNotFoundException("게시글을 찾을 수 없습니다."));
		boardRepository.delete(board);
	}
	
}
