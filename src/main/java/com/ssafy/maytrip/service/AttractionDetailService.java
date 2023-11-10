package com.ssafy.maytrip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.AttractionDetail;
import com.ssafy.maytrip.domain.AttractionInfo;
import com.ssafy.maytrip.dto.AttractionDetailDto;
import com.ssafy.maytrip.dto.AttractionInfoDto;
import com.ssafy.maytrip.repository.AttractionDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionDetailService {
	private final AttractionDetailRepository attractionDetailRepository;
	
//	public List<AttractionDetailDto> getAttractionByCategory(String cat1,String cat2, String cat3){
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~0");
//		System.out.println(cat1);
//
//		List<AttractionDetail> attraction = attractionDetailRepository.findAllByCat1AndCat2AndCat3(cat1,cat2,cat3);
//		System.out.println("a "+ attractionDetailRepository.findAllByCat1AndCat2AndCat3(cat1,cat2,cat3));
//		List<AttractionDetailDto> list = new ArrayList<AttractionDetailDto>(); 
//		if(attraction!=null) {
//			for(AttractionDetail row : attraction) {
//				AttractionDetailDto dto = null;
//				dto = AttractionDetailDto.builder()
//						.contentId(row.getAttractionInfo().getContentId())
//						.cat1(row.getCat1())
//						.cat2(row.getCat2())
//						.cat3(row.getCat3())
//						.createdTime(row.getCreatedTime())
//						.modifiedTime(row.getModifiedTime())
//						.booktour(row.getBooktour())
//						.build();
//
//				list.add(dto);
//			}
//		}
//		return list;
//	}
}
