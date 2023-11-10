package com.ssafy.maytrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.AttractionInfo;


public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer> {
	
	// 관관지 전체 정보 조회
	List<AttractionInfo> findAll();
	
	// 시도, 구군으로 관광지 정보 조회
	List<AttractionInfo> findBySidoCodeAndGugunCode(int sidoCode, int gugunCode);
}
