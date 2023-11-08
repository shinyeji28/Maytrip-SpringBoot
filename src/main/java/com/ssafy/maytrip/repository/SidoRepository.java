package com.ssafy.maytrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.SidoEntity;

public interface SidoRepository extends JpaRepository<SidoEntity, Integer> {

	List<SidoEntity> findAll();

}
