package com.ssafy.maytrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.maytrip.domain.TravelDay;

public interface TravelDayRepository extends JpaRepository<TravelDay, Integer> {

	List<TravelDay> findAllByCrewId(int crewId);

//    @Query(value = "with A as(select * from travel_day ) " +
//            "delete from travel_day " +
//            "where day = ( select max(day) from travel_day where crew_id = :crewId group by crew_id) " +
//            "and crew_id = :crewId",
//            nativeQuery = true)
//    void deleteByLastPriority(int crewId);
}
