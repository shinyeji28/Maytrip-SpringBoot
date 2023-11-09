package com.ssafy.maytrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.Sido;

public interface SidoRepository extends JpaRepository<Sido, Integer> {

	List<Sido> findAll();

}
