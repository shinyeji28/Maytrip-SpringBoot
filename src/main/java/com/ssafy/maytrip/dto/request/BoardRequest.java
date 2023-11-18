package com.ssafy.maytrip.dto.request;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.dto.FileInfoDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BoardRequest {
	
	private Integer id;

	private int memberId;
		
	private String title;

	private String content;

	@Schema(type = "string")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@Schema(type = "string")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	private int headcount;
		
	private int sidoCode;
	
	private int gugunCode;
	
	private FileInfoDto thumbnail;
	private List<FileInfoDto> fileInfos;

}
