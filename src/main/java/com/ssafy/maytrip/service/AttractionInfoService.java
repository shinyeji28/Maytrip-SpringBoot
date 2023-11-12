package com.ssafy.maytrip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.AttractionDetail;
import com.ssafy.maytrip.domain.AttractionInfo;
import com.ssafy.maytrip.dto.AttractionInfoDto;
import com.ssafy.maytrip.repository.AttractionInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionInfoService {
	private final AttractionInfoRepository attractionInfoRepository;
	
	public List<AttractionInfoDto> getAllAttraction(double lat, double lon, double radiusInKm){
		List<AttractionInfo> attraction = attractionInfoRepository.findAllByLatAndLon(lat, lon, radiusInKm);
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
		List<AttractionInfo> attraction = attractionInfoRepository.findByGugunAndSido(sidoCode,gugunCode);
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
	
	// 카테고리별 관광지 조회
	public List<AttractionInfoDto> getAttractionByCategory(String cat1,String cat2, String cat3){

		List<AttractionInfo> attraction = null;
		if(cat1!="" && cat2!="" && cat3!="") {
			attraction = attractionInfoRepository.findAllByCat1AndCat2AndCat3(cat1,cat2,cat3);			
		}else if(cat1!="" && cat2!="") {
			attraction = attractionInfoRepository.findAllByCat1AndCat2(cat1,cat2);			
		}else {
			attraction = attractionInfoRepository.findAllByCat1(cat1);			
		}
	
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
	
	// 단어로 관광지 정보 조회
	public List<AttractionInfoDto> getAttractionByWord(String key, String word){
		List<AttractionInfo> attraction = null;
		if("title".equals(key)) {
			attraction = attractionInfoRepository.findAllByTitle(word);			
		}else if("address".equals(key)) {
			attraction = attractionInfoRepository.findAllByAddr1(word);			
		}
		List<AttractionInfoDto> list = new ArrayList<AttractionInfoDto>(); 
		if(!attraction.isEmpty()) {
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

