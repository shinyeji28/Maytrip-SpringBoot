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
		List<AttractionInfo> attraction = attractionInfoRepository.findAll();
		List<AttractionInfoDto> list = new ArrayList<AttractionInfoDto>();
		if(attraction!=null) {
			for(AttractionInfo row : attraction) {
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
						.sidoCode(row.getGugun().getGugunId().getSido().getSidoCode())
						.gugunCode(row.getGugun().getGugunId().getGugunCode())
						.latitude(row.getLatitude())
						.longitude(row.getLongitude())
						.mlevel(row.getMlevel())
						.build();

				list.add(dto);
			}
		}
		return list;
	}
	
	// 시도, 구군 별 관광지 정보 조회
	public List<AttractionInfoDto> getAttractionBySidoGugun(int sidoCode, int gugunCode){
		List<AttractionInfo> attraction = attractionInfoRepository.findByGugunGugunIdGugunCodeAndGugunGugunIdSidoSidoCode(sidoCode,gugunCode);
		List<AttractionInfoDto> list = new ArrayList<AttractionInfoDto>();
		if(attraction!=null) {
			for(AttractionInfo row : attraction) {
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
						.sidoCode(row.getGugun().getGugunId().getSido().getSidoCode())
						.gugunCode(row.getGugun().getGugunId().getGugunCode())
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

