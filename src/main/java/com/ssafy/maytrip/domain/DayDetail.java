package com.ssafy.maytrip.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="day_detail")
public class DayDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name="detail_id")
	private int detailId;
	
	@ManyToOne
	@JoinColumn(name="day_id", referencedColumnName = "day_id")
	private TravelDay travelDay;
	
	@ManyToOne
	@JoinColumn(name="content_id", referencedColumnName = "content_id")
	private AttractionInfo attractionInfo;
	
	private int priority;

	public void decreasePriority() {
		this.priority--;
	}

}
