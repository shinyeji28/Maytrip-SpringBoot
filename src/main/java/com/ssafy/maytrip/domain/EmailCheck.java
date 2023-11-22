package com.ssafy.maytrip.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailCheck {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "check_id")
	private int checkId;
	
	private String code;
	
	@OneToOne
	@JoinColumn(name = "member_id")
	private Member member;
}
