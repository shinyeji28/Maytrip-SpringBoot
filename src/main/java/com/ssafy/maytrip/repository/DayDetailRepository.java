package com.ssafy.maytrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.DayDetail;

public interface DayDetailRepository extends JpaRepository<DayDetail, Integer> {

	List<DayDetail> findAllByTravelDayDayId(int dayId);
    
}
