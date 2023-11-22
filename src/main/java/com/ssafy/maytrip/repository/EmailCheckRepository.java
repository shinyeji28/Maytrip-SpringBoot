package com.ssafy.maytrip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.EmailCheck;

public interface EmailCheckRepository extends JpaRepository<EmailCheck, Integer>{

	Optional<EmailCheck> findByMemberId(int memberId);

	Optional<EmailCheck> findByMemberEmailAndCode(String email, String code);

	
}
