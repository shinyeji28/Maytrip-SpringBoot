package com.ssafy.maytrip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.AttractionInfo;
import com.ssafy.maytrip.dto.AttractionInfoDto;
import com.ssafy.maytrip.repository.AttractionInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionInfoService {
	private final AttractionInfoRepository attractionInfoRepository;
	
	public List<AttractionInfoDto> getAllAttraction(){
		List<AttractionInfo> gugun = attractionInfoRepository.findAll();
		List<AttractionInfoDto> list = new ArrayList<AttractionInfoDto>();
		if(gugun!=null) {
			for(AttractionInfo row : gugun) {
				AttractionInfoDto dto = null;
				dto = AttractionInfoDto.builder()
						.contentId(row.getContentId())
						.contentTypeId(row.getContentTypeId())
						.title(row.getTitle())
						.addr1(row.getAddr1())
						.addr2(row.getAddr2())
						.zipcode(row.getZipcode())
						.tel(row.getTel())
						.firstImage(row.getFirstImage())
						.firstImage2(row.getFirstImage2())
						.readcount(row.getReadcount())
						.sidoCode(row.getSidoCode())
						.gugunCode(row.getGugunCode())
						.latitude(row.getLatitude())
						.longitude(row.getLongitude())
						.mlevel(row.getMlevel())
						.build();

				list.add(dto);
			}
		}
		return list;
	}
}

