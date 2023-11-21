package com.ssafy.maytrip.dto.response;

import com.ssafy.maytrip.domain.AttractionDescription;
import com.ssafy.maytrip.domain.AttractionInfo;
import com.ssafy.maytrip.dto.AttractionInfoDto;

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
public class AttractionDescriptionResponse {
	private AttractionInfoDto attractionInfo;
	private String overview;
	
	public static AttractionDescriptionResponse from(AttractionDescription description) {
		return AttractionDescriptionResponse.builder()
				.attractionInfo(AttractionInfoDto.from(description.getAttractionInfo()))
				.overview(description.getOverview())
				.build();
	}
}
