package com.ssafy.maytrip.dto.response;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.maytrip.domain.DayDetail;
import com.ssafy.maytrip.domain.TravelDay;
import com.ssafy.maytrip.dto.response.AttractionDescriptionResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PlanResponse {

    private List<Day> days;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static class Day {
    	
    	private int dayId;
    	
    	private int day;        
    	
    	private List<Detail> details;
    	
    	public static PlanResponse.Day from(TravelDay travelDay, List<PlanResponse.Detail> planDetails){
    		return PlanResponse.Day.builder()
	                .dayId(travelDay.getDayId())
	                .day(travelDay.getDay())
	                .details(planDetails)
	                .build();
    	}

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static class Detail {
    	private int detailId;
        private int contentId;
        private int priority;
        
        public static PlanResponse.Detail from(DayDetail dayDetail) {
        	return PlanResponse.Detail.builder()
					.detailId(dayDetail.getDetailId())
					.priority(dayDetail.getPriority())
					.build();
        }
    }

}
