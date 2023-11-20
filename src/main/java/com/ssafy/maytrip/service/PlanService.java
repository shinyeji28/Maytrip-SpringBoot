package com.ssafy.maytrip.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.maytrip.domain.AttractionInfo;
import com.ssafy.maytrip.domain.Crew;
import com.ssafy.maytrip.domain.DayDetail;
import com.ssafy.maytrip.domain.TravelDay;
import com.ssafy.maytrip.dto.request.DetailRequest;
import com.ssafy.maytrip.dto.request.PlanRequest;
import com.ssafy.maytrip.dto.response.DetailResponse;
import com.ssafy.maytrip.dto.response.PlanResponse;
import com.ssafy.maytrip.exception.IdNotFoundException;
import com.ssafy.maytrip.repository.AttractionInfoRepository;
import com.ssafy.maytrip.repository.CrewRepository;
import com.ssafy.maytrip.repository.DayDetailRepository;
import com.ssafy.maytrip.repository.TravelDayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanService {

	private final CrewRepository crewRepository;
	private final TravelDayRepository travelDayRepository;
	private final DayDetailRepository dayDetailRepository;
	private final AttractionInfoRepository attractionInfoRepository;
	
	@Transactional
	public void insertTravelDay(PlanRequest planRequest) {
		List<PlanRequest.Day> days = planRequest.getDays();

        for (PlanRequest.Day day : days) {
            // travelDay를 저장하고 travelDay의 ID를 얻어옴
        	
			Crew crew = crewRepository.findById(planRequest.getCrewId())
					.orElseThrow(() -> new IdNotFoundException("크루 ID를 찾을 수 없습니다."));
			
			Optional<TravelDay> optionalTravelDay = travelDayRepository.findById(day.getDayId());
			TravelDay travelDay;
			if(optionalTravelDay.isPresent()) {
				travelDay = optionalTravelDay.get();
			} else {
				travelDay = TravelDay.builder()
	        			.crew(crew)
	        			.day(day.getDay())
	        			.build();					
			}
        	
        	travelDay = travelDayRepository.save(travelDay);
            
            // details를 저장
            for (PlanRequest.Detail detail : day.getDetails()) {
            	
                AttractionInfo attractionInfo = AttractionInfo.builder()
                        .contentId(detail.getContentId())
                        .build();
                
                DayDetail dayDetail = DayDetail.builder()
                        .travelDay(travelDay)
                        .attractionInfo(attractionInfo)
                        .priority(detail.getPriority())
                        .build();

            	dayDetailRepository.save(dayDetail);
            }
        }
	}
	
	@Transactional
	public PlanResponse selectPlan(int crewId) {	
		
		List<TravelDay> travleDays = travelDayRepository.findAllByCrewId(crewId);
		PlanResponse planResponse = new PlanResponse();
		
	    List<PlanResponse.Day> planDays = new ArrayList<>();
		for(TravelDay day : travleDays) {
			List<PlanResponse.Detail> planDetails = new ArrayList<>();
			for(DayDetail detail : day.getDayDetails()) {
				// 여행 일정에 대한 세부사항을 PlanResponse.Day 객체로 매핑				
	            planDetails.add(PlanResponse.Detail.from(detail));
			}

	        // 여행 일정을 PlanResponse.Day 객체로 매핑
	        planDays.add(PlanResponse.Day.from(day, planDetails));
		}
		
		PlanResponse resultDto = PlanResponse.builder()
				.days(planDays)
				.build();
		return resultDto;
		
	}
	
	@Transactional
	public void updatePlan(PlanRequest planRequest) {
		
		for(PlanRequest.Day day : planRequest.getDays()) {
			for(PlanRequest.Detail detail : day.getDetails()) {
				
				// detail_id가 없다면 insert
				if(detail.getDetailId() == 0) {
					AttractionInfo attractionInfo = attractionInfoRepository.findById(detail.getContentId())
							.orElseThrow(() -> new IdNotFoundException("관광지 정보를 찾을 수 없습니다."));
					TravelDay travelDay = travelDayRepository.findById(day.getDayId())
							.orElseThrow(() -> new IdNotFoundException("여행 일자를 찾을 수 없습니다."));
					DayDetail dayDetail = DayDetail.builder()
							.attractionInfo(attractionInfo)
							.priority(detail.getPriority())
							.travelDay(travelDay)
							.build();
					dayDetailRepository.save(dayDetail);
					
				}else {				// detail_id가 있다면 update
					dayDetailRepository.updateDetail(detail.getContentId(), detail.getPriority(), detail.getDetailId(), day.getDayId());
					
				}
				
			}
		}
		
	}

	public DetailResponse insertDetail(DetailRequest detailRequest) {
		AttractionInfo attractionInfo = attractionInfoRepository.findById(detailRequest.getContentId())
				.orElseThrow(() -> new IdNotFoundException("관광지 정보를 찾을 수 없습니다."));
		TravelDay travelDay = travelDayRepository.findById(detailRequest.getDayId())
				.orElseThrow(() -> new IdNotFoundException("여행 일자를 찾을 수 없습니다."));
		
		DayDetail dayDetail = DayDetail.builder()
				.attractionInfo(attractionInfo)
				.priority(detailRequest.getPriority())
				.travelDay(travelDay)
				.build();
		DayDetail detail = dayDetailRepository.save(dayDetail);
		return DetailResponse.from(detail);
	}

	public void deleteDetail(int detailId) {
		DayDetail detail = dayDetailRepository.findById(detailId)
				.orElseThrow(() -> new IdNotFoundException("여행지 일정을 찾을 수 없습니다."));
		dayDetailRepository.delete(detail);
	}
	
	
	
}
