package com.ssafy.maytrip.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Builder
@Table(name="file_info")
public class FileInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileId;
	
	private String saveFolder;    // 저장 파일 이름 

	private String saveFile;  // 저장 파일 이름

	private String originalFile;  // 원본 파일 이름
	
	@OneToOne
	@JoinColumn(name="board_id", referencedColumnName = "board_id")
	private Board board;
}
