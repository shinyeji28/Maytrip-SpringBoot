package com.ssafy.maytrip.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.maytrip.domain.Board;
import com.ssafy.maytrip.domain.FileInfo;
import com.ssafy.maytrip.dto.FileInfoDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BoardResponse {
	
	private int id;
	
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
	
	private LocalDateTime registDate;

	private int headcount;
		
	private String sidoName;
	
	private String gugunName;
	
	private int sidoCode;
	
	private int gugunCode;
	
	private int views;
	
	private FileInfoDto thumbnail;
	
	private List<FileInfoDto> fileInfos;
	
	public static BoardResponse from(Board board) {	
		
		return BoardResponse.builder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.registDate(board.getRegistDate())
				.startDate(board.getStartDate())
				.endDate(board.getEndDate())
				.headcount(board.getHeadcount())
				.views(board.getViews())
				.sidoName(board.getGugun().getGugunId().getSido().getSidoName())
				.gugunName(board.getGugun().getGugunName())
				.sidoCode(board.getGugun().getGugunId().getSido().getSidoCode())
				.gugunCode(board.getGugun().getGugunId().getGugunCode())
				.thumbnail(FileInfoDto.from(board.getThumbnail()))
				.fileInfos(FileInfoDto.listFrom(board.getFileInfos()))
				.build();
	}
	
}
