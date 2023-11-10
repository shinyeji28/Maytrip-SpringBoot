package com.ssafy.maytrip.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="gugun")
public class Gugun {
	
	@EmbeddedId
	private GugunId gugunId;

	@Column(name="gugun_name")
	private String gugunName;
	
}
