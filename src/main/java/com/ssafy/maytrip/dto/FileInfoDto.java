package com.ssafy.maytrip.dto;

import java.util.List;

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

}
