package com.ssafy.maytrip.dto;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.dto.request.PlanRequest.Day;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FileInfoDto {

	private String saveFolder;
	private String originalFile;
	private String saveFile;
	
	public static FileInfoDto from(FileInfo fileInfo) {
		if(fileInfo == null) return null;
		return FileInfoDto.builder()
				.saveFolder(fileInfo.getSaveFolder())
				.originalFile(fileInfo.getOriginalFile())
				.saveFile(fileInfo.getSaveFile())
				.build();
	}
	
//	public static List<FileInfoDto> listFrom(List<FileInfo> fileInfos) {
//		if(fileInfos == null) return null;
//		
//		return files;
//	}

}
