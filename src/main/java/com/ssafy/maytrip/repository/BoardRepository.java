package com.ssafy.maytrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
