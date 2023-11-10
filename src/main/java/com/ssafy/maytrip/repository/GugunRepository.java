package com.ssafy.maytrip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.Gugun;
import com.ssafy.maytrip.domain.GugunId;

public interface GugunRepository extends JpaRepository<Gugun, GugunId> {

	List<Gugun> findAll();

	List<Gugun> findAllByGugunIdSidoCode(int sidoCode);
	
	Optional<Gugun> findByGugunIdSidoCodeAndGugunIdGugunCode(int sidoCode, int gugunCode);
}
