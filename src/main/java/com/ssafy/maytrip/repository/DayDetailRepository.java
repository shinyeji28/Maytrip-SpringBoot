package com.ssafy.maytrip.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.maytrip.domain.DayDetail;

public interface DayDetailRepository extends JpaRepository<DayDetail, Integer> {

	List<DayDetail> findAllByTravelDayDayId(int dayId);
	
    @Modifying
    @Query(value="insert into day_detail(priority, content_id, day_id)\r\n" + 
    		"values(priority=:priority, content_id=:contentId, day_id=:dayId)", nativeQuery = true)
	void insertDetail(
    		@Param("contentId") int contentId, 
    		@Param("priority") int priority, 
    		@Param("dayId") int dayId);
	
	
    @Modifying
    @Query(value="UPDATE day_detail SET content_id = :contentId, priority = :priority, day_id = :dayId WHERE detail_id = :detailId", nativeQuery = true)
    void updateDetail(
    		@Param("contentId") int contentId, 
    		@Param("priority") int priority, 
    		@Param("detailId") int detailId, 
    		@Param("dayId") int dayId);
	    
}
