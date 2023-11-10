package com.ssafy.maytrip.domain;


import javax.persistence.CascadeType;
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
public class Crew {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "crew_id")
	private int id;
	
	@Column(name="crew_name")
	private String crewName;
		
	private int cost;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "board_id")
	private Board board;
}
