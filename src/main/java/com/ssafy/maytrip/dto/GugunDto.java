package com.ssafy.maytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GugunDto {
	private int gugunCode;
	private String gugunName;
	private int sidoCode;
	
	@Builder
	public GugunDto(int gugunCode, String gugunName, int sidoCode) {
		super();
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
		this.sidoCode = sidoCode;
	}
	
	

}
