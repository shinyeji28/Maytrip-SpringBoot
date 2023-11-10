package com.ssafy.maytrip.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private int id;
	
	private String title;

	private String content;

	private LocalDate startDate;
	
	private LocalDate endDate;

	private int headcount;
	
	private int views;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime registDate;	
	
	@PrePersist
	public void onPrePersist() {
	    String customLocalDateTimeFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	    LocalDateTime parsedCreateDate = LocalDateTime.parse(customLocalDateTimeFormat, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	    this.registDate = parsedCreateDate;
	}
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name= "gugun_code", referencedColumnName = "gugun_code"),
			@JoinColumn(name= "sido_code", referencedColumnName = "sido_code")
	})
	private Gugun gugun;
	
	public void updateViews() {
		views++;
	}
}
