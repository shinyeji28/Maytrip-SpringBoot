package com.ssafy.maytrip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.CrewMapping;

public interface CrewMappingRepository extends JpaRepository<CrewMapping, Integer>{

	Optional<CrewMapping> findByCrewIdAndMemberId(int crewId, int memberId);

    List<CrewMapping> findAllByMemberId(int memberId);

    List<CrewMapping> findAllByCrewId(int id);

	Long countByCrewId(int id);
}
