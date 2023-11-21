package com.ssafy.maytrip.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DetailRequest {
	
	private int detailId;
	
	private int dayId;
	
	private int priority;
	
	private int contentId;
}
