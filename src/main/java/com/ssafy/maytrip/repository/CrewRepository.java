package com.ssafy.maytrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.maytrip.domain.Crew;

public interface CrewRepository extends JpaRepository<Crew, Integer>{

	Crew findByBoardId(int id);

	@Query(value="update crew set review_id = :id where crew_id = :crewId", nativeQuery = true)
	void updateReview(int crewId, int id);




}
