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

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private int id;
	
	@Column(columnDefinition = "int DEFAULT 0")
	private int likes;
	
	@Column(name = "title", length = 1000)
	private String title;
	
	@Column(name = "content", length = 5000)
	private String content;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name= "thumbnail", referencedColumnName = "file_id", nullable = true)
	private FileInfo fileInfo;
	
	@OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FileInfo> fileInfos;
}
