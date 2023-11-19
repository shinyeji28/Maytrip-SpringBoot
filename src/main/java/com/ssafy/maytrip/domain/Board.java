package com.ssafy.maytrip.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.*;

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

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToOne(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private Crew crew;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL , orphanRemoval = true)
	private List<FileInfo> fileInfos;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private FileInfo thumbnail;
	
	public void updateViews() {
		views++;
	}
}
