package com.ssafy.maytrip.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.maytrip.domain.TravelDay;
import com.ssafy.maytrip.dto.response.PlanResponse.Day;

public interface TravelDayRepository extends JpaRepository<TravelDay, Integer> {

	List<TravelDay> findAllByCrewId(int crewId);

    @Query(value = "with A as(select * from travel_day ) " +
            "delete from travel_day " +
            "where day = ( select max(day) from travel_day where crew_id = :crewId group by crew_id) " +
            "and crew_id = :crewId",
            nativeQuery = true)
    void deleteByLastPriority(int crewId);
}
