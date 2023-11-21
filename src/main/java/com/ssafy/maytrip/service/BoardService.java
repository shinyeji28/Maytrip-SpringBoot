package com.ssafy.maytrip.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.maytrip.domain.Board;
import com.ssafy.maytrip.domain.Crew;
import com.ssafy.maytrip.domain.CrewMapping;
import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.domain.Gugun;
import com.ssafy.maytrip.domain.Member;
import com.ssafy.maytrip.domain.TravelDay;
import com.ssafy.maytrip.dto.FileInfoDto;
import com.ssafy.maytrip.dto.request.BoardRequest;
import com.ssafy.maytrip.dto.response.BoardResponse;
import com.ssafy.maytrip.exception.IdNotFoundException;
import com.ssafy.maytrip.repository.BoardRepository;
import com.ssafy.maytrip.repository.CrewMappingRepository;
import com.ssafy.maytrip.repository.CrewRepository;
import com.ssafy.maytrip.repository.FileInfoRepository;
import com.ssafy.maytrip.repository.GugunRepository;
import com.ssafy.maytrip.repository.MemberRepository;
import com.ssafy.maytrip.repository.TravelDayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final GugunRepository gugunRepository;
	private final CrewRepository crewRepository;
	private final MemberRepository memberRepository;
	private final CrewMappingRepository crewMappingRepository;
	private final FileInfoRepository fileInfoRepository;
	private final TravelDayRepository travelDayRepository;

//	@Transactional
//	public int regist(BoardRequest boardDto) {
//		Gugun gugun = gugunRepository.findByGugunIdSidoSidoCodeAndGugunIdGugunCode(boardDto.getSidoCode(), boardDto.getGugunCode())
//				.orElseThrow(() -> new IdNotFoundException("gugun code가 존재하지 않습니다."));
//		Member member = memberRepository.findById(boardDto.getMemberId())
//				.orElseThrow(() -> new IdNotFoundException("회원이 존재하지 않습니다."));
//
//		Board board = Board.builder()
//				.title(boardDto.getTitle())
//				.content(boardDto.getContent())
//				.startDate(boardDto.getStartDate())
//				.endDate(boardDto.getEndDate())
//				.headcount(boardDto.getHeadcount())
//				.gugun(gugun)
//				.member(member)
//				.build();
//		board = boardRepository.save(board);
//		Crew crew = crewRepository.save(
//				Crew.builder()
//				.crewName(board.getTitle())
//				.board(board)
//				.build());
//		
//		CrewMapping crewMapping = CrewMapping.builder()
//				.crew(crew)
//				.member(member)
//				.build();
//		crewMappingRepository.save(crewMapping);
//
//		return board.getId();
//	}
	
	@Transactional
	public int regist(BoardRequest boardDto) {
		Gugun gugun = gugunRepository.findByGugunIdSidoSidoCodeAndGugunIdGugunCode(boardDto.getSidoCode(), boardDto.getGugunCode())
				.orElseThrow(() -> new IdNotFoundException("gugun code가 존재하지 않습니다."));
		Member member = memberRepository.findById(boardDto.getMemberId())
				.orElseThrow(() -> new IdNotFoundException("회원이 존재하지 않습니다."));
		
		FileInfo thumbfile = null;
		if(boardDto.getThumbnail()!=null) {
			thumbfile = FileInfo.builder()
					.saveFolder(boardDto.getThumbnail().getSaveFolder())
					.saveFile(boardDto.getThumbnail().getSaveFile())
					.originalFile(boardDto.getThumbnail().getOriginalFile())
					.build();
			
			thumbfile = fileInfoRepository.save(thumbfile);
		}
		Board board = Board.builder()
				.title(boardDto.getTitle())
				.content(boardDto.getContent())
				.startDate(boardDto.getStartDate())
				.endDate(boardDto.getEndDate())
				.headcount(boardDto.getHeadcount())
				.gugun(gugun)
				.member(member)
				.thumbnail(thumbfile)
				.build();
		board = boardRepository.save(board);
		
		if(board.getFileInfos()!=null) {
			for(FileInfoDto fileInfo : boardDto.getFileInfos()) {
				FileInfo file = FileInfo.builder()
						.saveFolder(fileInfo.getSaveFolder())
						.saveFile(fileInfo.getSaveFile())
						.originalFile(fileInfo.getOriginalFile())
						.board(board)
						.build();
				fileInfoRepository.save(file);
			}
		}
		
		
		Crew crew = crewRepository.save(
				Crew.builder()
				.crewName(board.getTitle())
				.board(board)
				.build());
		
		CrewMapping crewMapping = CrewMapping.builder()
				.crew(crew)
				.member(member)
				.build();
		crewMappingRepository.save(crewMapping);
		
		Period period = Period.between(board.getStartDate(), board.getEndDate());
		for(int i=1; i<=period.getDays()+1; i++) {
			travelDayRepository.save(
					TravelDay.builder()
					.crew(crew)
					.day(i)
					.build());
		}

		return board.getId();
	}
	
	@Transactional
	public List<BoardResponse> selectAll(Integer sidoCode, Integer gugunCode) {
		List<Board> boards = null;

		if(sidoCode!=null && gugunCode!=null) {
			Gugun gugun = gugunRepository.findByGugunIdSidoSidoCodeAndGugunIdGugunCode(sidoCode,gugunCode)
					.orElseThrow(() -> new IdNotFoundException("gugun code가 존재하지 않습니다."));
			Board board = Board.builder().gugun(gugun).build();
			boards = boardRepository.findAllByGugunGugunIdGugunCodeAndGugunGugunIdSidoSidoCode(gugunCode, sidoCode);
		}else {
			boards = boardRepository.findAll();
		}
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

	public BoardResponse modifySomeInfo(BoardRequest boardDto) {
		Board board = boardRepository.findById(boardDto.getId())
				.orElseThrow(() -> new IdNotFoundException("게시글을 찾을 수 없습니다."));

		board.updateInfos(boardDto);
		board = boardRepository.save(board);

		Period prevPeriod = Period.between(boardDto.getStartDate(), boardDto.getEndDate());
		Period currPeriod = Period.between(board.getStartDate(), board.getEndDate());
		if (prevPeriod.getDays() > currPeriod.getDays()) {
			for (int i=currPeriod.getDays(); i<prevPeriod.getDays(); i++) {
				travelDayRepository.deleteByLastPriority(board.getCrew().getId());
			}
		}
		else if (prevPeriod.getDays() < currPeriod.getDays()) {
			Crew crew = crewRepository.findById(board.getCrew().getId())
					.orElseThrow(() -> new IdNotFoundException("크루를 찾지 못했습니다."));
			for(int i= prevPeriod.getDays()+2; i <= currPeriod.getDays()+1; i++) {
				travelDayRepository.save(
						TravelDay.builder()
								.crew(crew)
								.day(i)
								.build());
			}
		}

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
