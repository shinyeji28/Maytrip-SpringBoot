package com.ssafy.maytrip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.Gugun;
import com.ssafy.maytrip.dto.GugunDto;
import com.ssafy.maytrip.repository.GugunRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GugunService {
	private final GugunRepository gugunRepository;
	
	public List<GugunDto> getAllGugun(){
		List<Gugun> gugun = gugunRepository.findAll();
		List<GugunDto> list = new ArrayList<GugunDto>();
		if(gugun!=null) {
			for(Gugun row : gugun) {
				GugunDto dto = null;
				dto = GugunDto.builder()
						.gugunCode(row.getGugunCode())
						.gugunName(row.getGugunName())
						.sidoCode(row.getSidoCode())
						.build();
				list.add(dto);
			}
		}
		return list;
	}
	
	// sidoCode별 구군 조회
	public List<GugunDto> getGugunBySidoCode(int sidoCode){
		List<Gugun> gugun = gugunRepository.findBySidoCode(sidoCode);
		List<GugunDto> list = new ArrayList<GugunDto>();
		if(gugun!=null) {
			for(Gugun row : gugun) {
				GugunDto dto = null;
				dto = GugunDto.builder()
						.gugunCode(row.getGugunCode())
						.gugunName(row.getGugunName())
						.sidoCode(row.getSidoCode())
						.build();
				list.add(dto);
			}
		}
		return list;
	}
}
