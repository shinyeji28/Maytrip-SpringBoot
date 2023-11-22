package com.ssafy.maytrip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.maytrip.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	Optional<Member> findByUsernameAndPassword(String username, String password);

	Optional<Member> findByEmail(String email);
}
