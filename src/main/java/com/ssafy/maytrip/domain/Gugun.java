package com.ssafy.maytrip.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.sun.istack.NotNull;

import lombok.Builder.Default;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="gugun")
public class Gugun {
	@Id
	@NotNull
	@Column(name="gugun_code")
	private int gugunCode;
	
	@Column(name="gugun_name")
	private String gugunName;
	
	@NotNull
	@Column(name="sido_code")
	private int sidoCode;

}
