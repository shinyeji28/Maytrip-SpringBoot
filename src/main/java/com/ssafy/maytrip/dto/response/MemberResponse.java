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
    
    private String email;

    private String token;
    
    private FileInfoResponse profileImg;

    public static MemberResponse from(Member member) {
        FileInfoResponse file = null;
        if(member.getProfileImg() != null) file = FileUpload.toImageUrl(member.getProfileImg());
    	return MemberResponse.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .email(member.getEmail())
                .profileImg(file)
                .build();
    }

    public static MemberResponse from(String token) {
        return MemberResponse.builder()
                .token(token)
                .build();
    }
}
