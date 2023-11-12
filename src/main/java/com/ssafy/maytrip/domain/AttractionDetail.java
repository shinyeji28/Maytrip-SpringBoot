package com.ssafy.maytrip.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
public class AttractionDetail{
	
	@Id
	@Column(name = "content_id")
	private int contentId;
	
	@MapsId
	@OneToOne
	@JoinColumn(name="content_id", nullable=false, referencedColumnName="content_id")
	private AttractionInfo attractionInfo;
	
	@Column(length=3)	
	private String cat1;
	@Column(length=5)	
	private String cat2;
	@Column(length=9)	
	private String cat3;
	@Column(name="created_time", length=14)	
	private LocalDateTime createdTime;
	@Column(name="modified_time", length=14)	
	private LocalDateTime modifiedTime;
	@Column(length=5)	
	private String booktour;
}
