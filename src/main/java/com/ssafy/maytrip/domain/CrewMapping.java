package com.ssafy.maytrip.domain;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "crew_mapping")
public class CrewMapping {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="crew_mapping_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "crew_id", nullable = false)
	private Crew crew;
	
	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;
}
