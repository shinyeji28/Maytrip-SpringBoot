package com.ssafy.maytrip.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.maytrip.domain.*;
import com.ssafy.maytrip.repository.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.maytrip.dto.request.BoardRequest;
import com.ssafy.maytrip.dto.response.BoardResponse;
import com.ssafy.maytrip.exception.IdNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final GugunRepository gugunRepository;
	private final CrewRepository crewRepository;
	private final MemberRepository memberRepository;
	private final CrewMappingRepository crewMappingRepository;
	
	@Transactional
	public int regist(BoardRequest boardDto) {
		Gugun gugun = gugunRepository.findByGugunIdSidoSidoCodeAndGugunIdGugunCode(boardDto.getSidoCode(), boardDto.getGugunCode())
				.orElseThrow(() -> new IdNotFoundException("gugun code가 존재하지 않습니다."));
		Member member = memberRepository.findById(boardDto.getMemberId())
				.orElseThrow(() -> new IdNotFoundException("회원이 존재하지 않습니다."));

		Board board = Board.builder()
				.title(boardDto.getTitle())
				.content(boardDto.getContent())
				.startDate(boardDto.getStartDate())
				.endDate(boardDto.getEndDate())
				.headcount(boardDto.getHeadcount())
				.gugun(gugun)
				.member(member)
				.crew(Crew.builder()
						.crewName(boardDto.getTitle())
						.build())
				.build();
		board = boardRepository.save(board);

		CrewMapping crewMapping = CrewMapping.builder()
				.crew(board.getCrew())
				.member(member)
				.build();
		crewMappingRepository.save(crewMapping);

		return board.getId();
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

	@Transactional
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
	
	public List<BoardResponse> getListBoard(Map<String, String> map){

		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno")) - 1;
	    int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));

	    // PageRequest를 사용하여 페이징 및 정렬 설정 (currentPage는 0부터 시작하므로 1을 뺍니다)
	    PageRequest pageRequest = PageRequest.of(currentPage, sizePerPage);
		
		// 요청 보내기
	    Page<Board> boardPage = boardRepository.findAll(pageRequest);
		
	    List<BoardResponse> boardList = new ArrayList<>();
		for(Board board : boardPage) {
			BoardResponse boardResponse = BoardResponse.from(board);
			boardList.add(boardResponse);
		}


		return boardList;
	}
	
}
