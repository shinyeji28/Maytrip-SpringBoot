package com.ssafy.maytrip.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.sun.istack.NotNull;

import lombok.Getter;

@Embeddable
@Getter
public class GugunId implements Serializable {

	@NotNull
	@Column(name="sido_code")
	private int sidoCode;
	
	@NotNull
	@Column(name="gugun_code")
	private int gugunCode;

}
