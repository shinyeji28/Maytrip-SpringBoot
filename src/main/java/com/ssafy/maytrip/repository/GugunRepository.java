package com.ssafy.maytrip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.Gugun;
import com.ssafy.maytrip.domain.Sido;

public interface GugunRepository extends JpaRepository<Gugun, Integer> {

	List<Gugun> findAll();
	List<Gugun> findBySidoCode(int sidoCode);
	
	Optional<Gugun> findBySidoCodeAndGugunCode(int sidoCode, int gugunCode);
}
