package com.ssafy.maytrip.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crew {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "crew_id")
	private int id;
	
	@Column(name="crew_name")
	private String crewName;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private int cost;
	

}
