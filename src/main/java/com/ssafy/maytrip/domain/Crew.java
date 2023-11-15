package com.ssafy.maytrip.domain;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
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
	
}
