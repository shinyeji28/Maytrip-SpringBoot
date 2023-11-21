package com.ssafy.maytrip.dto.response;

import com.ssafy.maytrip.domain.Member;
import com.ssafy.maytrip.file.FileUpload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class MemberResponse {

    private int memberId;

    private String username;

    private String name;

    private String token;
    
    private FileInfoResponse profileImg;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .profileImg(FileUpload.toImageUrl(member.getProfileImg()))
                .build();
    }

    public static MemberResponse from(String token) {
        return MemberResponse.builder()
                .token(token)
                .build();
    }
}
