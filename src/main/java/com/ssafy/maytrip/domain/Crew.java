package com.ssafy.maytrip.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crew {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "crew_id")
	private int id;
	
    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelDay> travelDays;
	
	@Column(name="crew_name")
	private String crewName;
			
	@OneToOne
	@JoinColumn(name = "board_id")
	private Board board;

	@OneToMany(mappedBy = "crew", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CrewMapping> crewMappings;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="review_id", nullable = true )
	private Review review;	
	
}
