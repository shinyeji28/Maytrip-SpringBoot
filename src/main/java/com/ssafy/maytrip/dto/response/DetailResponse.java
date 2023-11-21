package com.ssafy.maytrip.dto.response;

import com.ssafy.maytrip.domain.AttractionInfo;
import com.ssafy.maytrip.domain.DayDetail;
import com.ssafy.maytrip.dto.AttractionInfoDto;
import com.ssafy.maytrip.dto.request.DetailRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailResponse {

	private int detailId;
	
	private int dayId;
	
	private int priority;
	
	private AttractionInfoDto attractionInfo;
	
	public static DetailResponse from(DayDetail detail) {
		return DetailResponse.builder()
				.attractionInfo(AttractionInfoDto.from(detail.getAttractionInfo()))
				.detailId(detail.getDetailId())
				.dayId(detail.getTravelDay().getDayId())
				.priority(detail.getPriority())
				.build();
	}
}
