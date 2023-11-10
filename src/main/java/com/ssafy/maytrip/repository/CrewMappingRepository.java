package com.ssafy.maytrip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.CrewMapping;

public interface CrewMappingRepository extends JpaRepository<CrewMapping, Integer>{

	Optional<CrewMapping> findByCrewIdAndMemberId(int crewId, int memberId);

}
