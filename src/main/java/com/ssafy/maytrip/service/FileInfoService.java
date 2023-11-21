package com.ssafy.maytrip.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.dto.FileInfoDto;
import com.ssafy.maytrip.dto.response.FileInfoResponse;
import com.ssafy.maytrip.repository.FileInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileInfoService {

	private final FileInfoRepository fileInfoRepository;
	
	@Transactional
	public FileInfo regist(FileInfoDto fileInfoDto) {
		
		FileInfo fileInfo = FileInfo.builder()
				.saveFolder(fileInfoDto.getSaveFolder())
				.originalFile(fileInfoDto.getOriginalFile())
				.saveFile(fileInfoDto.getSaveFile())
				.build();
		
		fileInfo = fileInfoRepository.save(fileInfo);
		return fileInfo;
	}
}
