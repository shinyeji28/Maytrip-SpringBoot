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
import com.ssafy.maytrip.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	@Value("${file.path}")
	private String uploadPath;
	
	@Value("${file.path.upload-images}")
	private String uploadImagePath;
	
	@Value("${file.path.upload-files}")
	private String uploadFilePath;
	
//	@PostMapping
//	public ResponseEntity<Integer> regist(@RequestBody BoardRequest boardDto) {
//		int savedId = boardService.regist(boardDto);
//		return ResponseEntity.ok(savedId);
//	}
	
	@PostMapping
	public ResponseEntity<Integer> regist(@ModelAttribute BoardRequest boardDto,
			@RequestParam(value="filess") List<MultipartFile> files,
			@RequestParam(value="thumbnaill") List<MultipartFile> thumbnail) {
		System.out.println();
		List<FileInfoDto> thumbFile = null;
		List<FileInfoDto> fileInfos = null;
		
		if(thumbnail!=null) {
			thumbFile= makeFileSource(thumbnail);
			for(FileInfoDto f : thumbFile) {
				boardDto.setThumbnail(f);
			}
		}
		if(files!=null) {
			fileInfos= makeFileSource(files);
			boardDto.setFileInfos(fileInfos);
		}
		
		int savedId = boardService.regist(boardDto);
//		return ResponseEntity.ok(savedId);
		return null;
	}
	
	public List<FileInfoDto> makeFileSource(List<MultipartFile> files) {
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadPath + File.separator + today;
		File folder = new File(saveFolder);
		if (!folder.exists())folder.mkdirs();
		
		List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
		for (MultipartFile mfile : files) {
			FileInfoDto fileInfoDto = new FileInfoDto();
			String originalFileName = mfile.getOriginalFilename();
			if (!originalFileName.isEmpty()) {
				String saveFileName = UUID.randomUUID().toString()
						+ originalFileName.substring(originalFileName.lastIndexOf('.'));
				fileInfoDto.setSaveFolder(today);
				fileInfoDto.setOriginalFile(originalFileName);
				fileInfoDto.setSaveFile(saveFileName);
				try {
					mfile.transferTo(new File(folder, saveFileName));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			fileInfos.add(fileInfoDto);
		}
		return fileInfos;
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
	public ResponseEntity<BoardResponse> modify(@RequestBody BoardRequest boardDto) {
		BoardResponse board = boardService.modify(boardDto);
		return ResponseEntity.ok(board);
	}
	
	@DeleteMapping("/{boardId}")
	public ResponseEntity<Void> delete(@PathVariable int boardId) {
		boardService.delete(boardId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
