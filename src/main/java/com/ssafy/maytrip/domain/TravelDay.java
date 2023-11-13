package com.ssafy.maytrip.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="travel_day")
public class TravelDay {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "day_id")
	private int dayId;
	
	@ManyToOne
	@JoinColumn(name="crew_id", referencedColumnName="crew_id")
	private Crew crew;
	
	@OneToMany(mappedBy = "travelDay", cascade = CascadeType.ALL , orphanRemoval = true)
	private List<DayDetail> dayDetails;
    
	
	@Column(name="day")
	private int day;        
	 
}
