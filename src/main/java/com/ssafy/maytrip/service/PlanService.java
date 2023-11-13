package com.ssafy.maytrip.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.AttractionInfo;
import com.ssafy.maytrip.domain.Crew;
import com.ssafy.maytrip.domain.DayDetail;
import com.ssafy.maytrip.domain.TravelDay;
import com.ssafy.maytrip.dto.request.PlanRequest;
import com.ssafy.maytrip.repository.DayDetailRepository;
import com.ssafy.maytrip.repository.TravelDayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanService {

	private final TravelDayRepository travelDayRepository;
	private final DayDetailRepository dayDetailRepository;
	
	@Transactional
	public void insertTravelDay(PlanRequest planRequest) {
		List<PlanRequest.Day> days = planRequest.getDays();

        for (PlanRequest.Day day : days) {
            // travelDay를 저장하고 travelDay의 ID를 얻어옴
        	TravelDay travelDay = TravelDay.builder()
        			.crew(Crew.builder().id(planRequest.getCrewId()).build())
        			.date(day.getDate())
        			.build();
        	int travelDayId = travelDayRepository.save(travelDay).getDayId();
            
            // details를 저장
            for (PlanRequest.Detail detail : day.getDetails()) {
            	
                AttractionInfo attractionInfo = AttractionInfo.builder()
                        .contentId(detail.getContentId())
                        .build();
                
                DayDetail dayDetail = DayDetail.builder()
                        .travelDay(TravelDay.builder().dayId(travelDayId).build())
                        .attractionInfo(attractionInfo)
                        .priority(detail.getPriority())
                        .build();
                

            	dayDetailRepository.save(dayDetail);
            }
        }
	}
	
	
}
