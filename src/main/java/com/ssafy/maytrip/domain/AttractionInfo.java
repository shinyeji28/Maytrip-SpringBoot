package com.ssafy.maytrip.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="attraction_info")
public class AttractionInfo {
	@Id
	@Column(name="content_id", nullable = false)
	private int contentId;
	@Column(name="content_type_id")
	private int contentTypeId;
	@Column(columnDefinition = "VARCHAR(100) DEFAULT 'null'")
	private String title;
	@Column(columnDefinition = "VARCHAR(100) DEFAULT 'null'")
	private String addr1;
	@Column(columnDefinition = "VARCHAR(50) DEFAULT 'null'")
	private String addr2;
	@Column(columnDefinition = "VARCHAR(50) DEFAULT 'null'")
	private String zipcode;
	@Column(columnDefinition = "VARCHAR(50) DEFAULT 'null'")
	private String tel;
	@Column(name="first_image", columnDefinition = "VARCHAR(200) DEFAULT 'null'")
	private String firstImage;
	@Column(name="first_image2", columnDefinition = "VARCHAR(200) DEFAULT 'null'")
	private String firstImage2;
	private int readcount;
	
//	@ManyToOne
//	@JoinColumn(name="sido_code", referencedColumnName = "sido_code")
//	private Sido sido;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="gugun_code", referencedColumnName = "gugun_code"),
		@JoinColumn(name="sido_code", referencedColumnName = "sido_code")		
	})
	private Gugun gugun;
	
	@Column(columnDefinition = "DECIMAL(20,17) DEFAULT 'null'")
	private double latitude;
	@Column(columnDefinition = "DECIMAL(20,17) DEFAULT 'null'")
	private double longitude;
	@Column(columnDefinition = "VARCHAR(2) DEFAULT 'null'")
	private String mlevel;
}
