package com.ssafy.maytrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.maytrip.domain.Board;


public interface BoardRepository extends JpaRepository<Board, Integer>{

	List<Board> findAllByGugunGugunIdGugunCodeAndGugunGugunIdSidoSidoCode(int gugunCode, int sidoCode);

	@Query(value = "update board " + 
			"set is_shared = not is_shared " + 
			"where board_id = :boardId",
			nativeQuery = true)
	@Modifying
	@Transactional
	void toggleIsShared(int boardId);


}
