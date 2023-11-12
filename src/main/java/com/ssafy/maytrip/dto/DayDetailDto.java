package com.ssafy.maytrip.dto;

import com.ssafy.maytrip.dto.response.AttractionDescriptionResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DayDetailDto {
	
	private int content_id;
	private int priority;
}
