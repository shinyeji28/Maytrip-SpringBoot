package com.ssafy.maytrip.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.dto.FileInfoDto;
import com.ssafy.maytrip.dto.request.BoardRequest;
import com.ssafy.maytrip.dto.response.BoardResponse;
import com.ssafy.maytrip.file.FileUpload;
import com.ssafy.maytrip.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	
//	@PostMapping
//	public ResponseEntity<Integer> regist(@RequestBody BoardRequest boardDto) {
//		int savedId = boardService.regist(boardDto);
//		return ResponseEntity.ok(savedId);
//	}
	
	@PostMapping
	public ResponseEntity<Integer> regist(@ModelAttribute BoardRequest boardDto,
			@RequestParam(value="image") MultipartFile thumbnail) {

		FileInfoDto thumbFile = null;
		
		if(thumbnail.getSize()!=0) {
			thumbFile= FileUpload.makeFileSource(thumbnail);
			boardDto.setThumbnail(thumbFile);
		}
		
		int savedId = boardService.regist(boardDto);
		return ResponseEntity.ok(savedId);
	}
	
	@GetMapping
	public ResponseEntity<List<BoardResponse>> selectAll(
			@RequestParam(name="sido", required = false) Integer sidoCode,
			@RequestParam(name="gugun", required = false) Integer gugunCode
			) {
		List<BoardResponse> list = boardService.selectAll(sidoCode, gugunCode);
		return ResponseEntity.ok(list);
	}
	
	
	
	@GetMapping("/search")
	public ResponseEntity<?> listBoard(@RequestParam Map<String, String> map) {

		List<BoardResponse> boardList = boardService.getListBoard(map);
		return ResponseEntity.ok(boardList);
	}
	
	@GetMapping("/{boardId}")
	public ResponseEntity<BoardResponse> selectByBoardId(@PathVariable int boardId) {
		BoardResponse board = boardService.selectByBoardId(boardId);
		return ResponseEntity.ok(board);
	}
	
	@PutMapping
	public ResponseEntity<BoardResponse> modify(@ModelAttribute BoardRequest boardDto,
			@RequestParam(value="image") MultipartFile thumbnail) {
		
		FileInfoDto thumbFile = null;

		if(thumbnail.getSize()!=0) {
			thumbFile= FileUpload.makeFileSource(thumbnail);
			boardDto.setThumbnail(thumbFile);
		}
		BoardResponse board = boardService.modify(boardDto);
		return ResponseEntity.ok(board);
	}

	@PutMapping("/detail")
	public ResponseEntity<BoardResponse> modifySomeInfo(@RequestBody BoardRequest boardDto) {
		BoardResponse board = boardService.modifySomeInfo(boardDto);
		return ResponseEntity.ok(board);
	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<Void> delete(@PathVariable int boardId) {
		boardService.delete(boardId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
