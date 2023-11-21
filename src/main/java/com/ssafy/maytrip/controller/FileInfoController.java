package com.ssafy.maytrip.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.dto.FileInfoDto;
import com.ssafy.maytrip.dto.response.FileInfoResponse;
import com.ssafy.maytrip.file.FileUpload;
import com.ssafy.maytrip.service.FileInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileInfoController {
	private final FileInfoService fileInfoService;
	
	
	@PostMapping
	public ResponseEntity<?> registFile(MultipartFile file){
		FileInfo fileInfo= fileInfoService.regist(FileUpload.makeFileSource(file));
		FileInfoResponse responseFile = FileUpload.toImageUrl(fileInfo);
		return ResponseEntity.ok(responseFile);
	}
	

	
}
