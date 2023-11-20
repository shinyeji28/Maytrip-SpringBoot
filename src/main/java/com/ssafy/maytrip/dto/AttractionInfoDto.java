package com.ssafy.maytrip.dto;

import com.ssafy.maytrip.domain.AttractionInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class AttractionInfoDto {
	private int contentId;
	private int contentTypeId;
	private String title;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String tel;
	private String firstImage;
	private String firstImage2;
	private int readcount;
	private SidoDto sido;
	private GugunDto gugun;
	private int sidoCode;
	private int gugunCode;
	private double latitude;
	private double longitude;
	private String mlevel;
	
	public static AttractionInfoDto from(AttractionInfo info) {
		return AttractionInfoDto.builder()
				.contentId(info.getContentId())
				.contentTypeId(info.getContentTypeId())
				.title(info.getTitle())
				.addr1(info.getAddr1())
				.addr2(info.getAddr2())
				.zipcode(info.getZipcode())
				.tel(info.getTel())
				.firstImage(info.getFirstImage())
				.firstImage2(info.getFirstImage2())
				.readcount(info.getReadcount())
				.sido(SidoDto.builder()
						.sidoCode(info.getGugun().getGugunId().getSido().getSidoCode())
						.sidoName(info.getGugun().getGugunId().getSido().getSidoName())
						.build())
				.gugun(GugunDto.builder()
						.gugunCode(info.getGugun().getGugunId().getGugunCode())
						.gugunName(info.getGugun().getGugunName())
						.build())
				.latitude(info.getLatitude())
				.longitude(info.getLongitude())
				.mlevel(info.getMlevel())
				.build();
	}
	
}
