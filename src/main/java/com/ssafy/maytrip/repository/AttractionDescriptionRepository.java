package com.ssafy.maytrip.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.AttractionDescription;

public interface AttractionDescriptionRepository extends JpaRepository<AttractionDescription, Integer> {
	
	AttractionDescription findOverviewByContentId(int contentId);
}
