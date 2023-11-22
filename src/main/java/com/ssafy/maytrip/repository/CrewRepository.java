package com.ssafy.maytrip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.maytrip.domain.Crew;
import com.ssafy.maytrip.domain.Review;

public interface CrewRepository extends JpaRepository<Crew, Integer>{

	Crew findByBoardId(int id);

	Crew findByReviewId(int id);


}
