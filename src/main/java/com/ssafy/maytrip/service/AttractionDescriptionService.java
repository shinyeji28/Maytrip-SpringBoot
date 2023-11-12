package com.ssafy.maytrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.AttractionDescription;
import com.ssafy.maytrip.dto.response.AttractionDescriptionResponse;
import com.ssafy.maytrip.repository.AttractionDescriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionDescriptionService {

	private final AttractionDescriptionRepository attractionDescriptionRepository;
	
	public AttractionDescriptionResponse getOverviewByContentId(int contentId) {
		AttractionDescription description = attractionDescriptionRepository.findOverviewByContentId(contentId);

		AttractionDescriptionResponse dto = null;
		if(description!=null) {
			 dto = AttractionDescriptionResponse.builder()
					.overview(description.getOverview())
					.build();
		 }
		return dto;
		
	}
}
