package com.ssafy.maytrip.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="trave_day")
public class TravelDay {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "day_id")
	private int dayId;
	
	@OneToOne
	@JoinColumn(name="crew_id", nullable=false, referencedColumnName="crew_id")
	private Crew crew;
	
	@Column(name="date")
	private LocalDateTime date;
	
	@Column(name="create_date")
	private LocalDateTime createDate;
}
