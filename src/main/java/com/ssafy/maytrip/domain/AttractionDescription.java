package com.ssafy.maytrip.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="attraction_description")
public class AttractionDescription {
	@Id
	@Column(name = "content_id")
	private int contentId;
	
	@OneToOne
    @JoinColumn(name="content_id", referencedColumnName="content_id")
	private AttractionInfo attractionInfo;
	
	@Column(length=10000)
	private String overview;

}
