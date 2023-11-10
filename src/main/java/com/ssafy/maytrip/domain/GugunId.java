package com.ssafy.maytrip.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

import lombok.Getter;

@Embeddable
@Getter
public class GugunId implements Serializable {

	@ManyToOne
	@JoinColumn(name="sido_code", referencedColumnName = "sido_code")
	private Sido sido;
		
	@NotNull
	@Column(name="gugun_code")
	private int gugunCode;

}
