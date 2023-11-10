package com.ssafy.maytrip.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="attraction_detail")
public class AttractionDetail {
	
	@Id
	@Column(name="content_id", nullable=false)	
	private int contentId;
	@Column(length=3)	
	private String cat1;
	@Column(length=5)	
	private String cat2;
	@Column(length=9)	
	private String cat3;
	@Column(name="created_time", length=14)	
	private String createdTime;
	@Column(name="modified_time", length=14)	
	private String modifiedTime;
	@Column(length=5)	
	private String booktour;
}
