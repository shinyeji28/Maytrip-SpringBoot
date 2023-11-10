package com.ssafy.maytrip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.Gugun;
import com.ssafy.maytrip.domain.GugunId;

public interface GugunRepository extends JpaRepository<Gugun, GugunId> {

	List<Gugun> findAll();
	
	List<Gugun> findAllByGugunIdSidoSidoCode(int sidoCode);
	
	Optional<Gugun> findByGugunIdSidoSidoCodeAndGugunIdGugunCode(int sidoCode, int gugunCode);
}
