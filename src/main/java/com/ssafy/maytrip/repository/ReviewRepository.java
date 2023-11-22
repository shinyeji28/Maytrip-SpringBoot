package com.ssafy.maytrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

}
