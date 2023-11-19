package com.ssafy.maytrip.dto.response;

import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.dto.FileInfoDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class FileInfoResponse {
	
	private int fileId;
	private String saveFolder;
	private String originalFile;
	private String saveFile;
	
	public static FileInfoResponse from(FileInfo fileInfo) {
		if(fileInfo == null) return null;
		return FileInfoResponse.builder()
				.fileId(fileInfo.getFileId())
				.saveFolder(fileInfo.getSaveFolder())
				.originalFile(fileInfo.getOriginalFile())
				.saveFile(fileInfo.getSaveFile())
				.build();
	}
}
