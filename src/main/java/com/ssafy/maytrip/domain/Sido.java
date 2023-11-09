package com.ssafy.maytrip.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="sido")
public class Sido {

    @Id
    @NotNull
    @Column(name="sido_code")
    private int sidoCode;

    @NotNull
    @Column(name="sido_name",length = 30)
    private String sidoName;

    @Builder
	public Sido(int sidoCode, String sidoName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}

    

    

}