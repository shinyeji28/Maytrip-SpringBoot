package com.ssafy.maytrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.FileInfo;


public interface FileInfoRepository extends JpaRepository<FileInfo, Integer> {

	FileInfo findByBoardId(Integer id);

	List<FileInfo> findAllByReviewId(int id);

}
