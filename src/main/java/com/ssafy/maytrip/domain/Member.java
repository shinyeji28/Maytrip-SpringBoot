package com.ssafy.maytrip.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ssafy.maytrip.dto.request.MemberRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private int id;
	
	private String username;
	private String password;
	private String name;

    public void update(MemberRequest memberRequest) {
		this.name = memberRequest.getName();
    }
}
