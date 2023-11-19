package com.ssafy.maytrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	List<Board> findAllByGugunGugunIdGugunCodeAndGugunGugunIdSidoSidoCode(int gugunCode, int sidoCode);


}
