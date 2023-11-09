package com.ssafy.maytrip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.SidoEntity;
import com.ssafy.maytrip.dto.SidoDto;
import com.ssafy.maytrip.repository.SidoRepository;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
@RequiredArgsConstructor
public class SidoService {
	
	private final SidoRepository sidoRepository;
	
	public List<SidoDto> getAllSido(){
		List<SidoEntity> sido = sidoRepository.findAll();
		List<SidoDto> list = new ArrayList<SidoDto>();
		if(sido!=null) {
			for(SidoEntity row : sido) {
				SidoDto dto = null;
				dto = SidoDto.builder()
						.sidoCode(row.getSidoCode())
						.sidoName(row.getSidoName())
						.build();
				list.add(dto);
			}
		}
		return list;
	}
	
}
