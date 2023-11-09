package com.ssafy.maytrip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SidoDto {
	private int sidoCode;
	private String sidoName;
	
	@Builder
	public SidoDto(int sidoCode, String sidoName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}
	
	
}
