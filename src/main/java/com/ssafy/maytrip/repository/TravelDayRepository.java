package com.ssafy.maytrip.repository;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.maytrip.domain.TravelDay;

public interface TravelDayRepository extends JpaRepository<TravelDay, Integer> {
	
	// 여행 일정 등록
    @Modifying
    @Query(value = "INSERT INTO TravelDay (crew_id, date) VALUES (:crewId, :date)", nativeQuery = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "day_id")
    int insertTravelDay(@Param("crewId") int crewId, @Param("date") LocalDate date);
    
}
