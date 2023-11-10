package com.ssafy.maytrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.AttractionDetail;

public interface AttractionDetailRepository extends JpaRepository<AttractionDetail, Integer> {
	// todo join	
	List<AttractionDetail> findAllByCat1AndCat2AndCat3(int cat1, int cat2, int cat3);
	
}
