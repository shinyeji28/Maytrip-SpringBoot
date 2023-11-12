package com.ssafy.maytrip.dto.response;

import com.ssafy.maytrip.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class MemberResponse {

    private int id;

    private String username;

    private String name;

    private String token;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .build();
    }

    public static MemberResponse from(String token) {
        return MemberResponse.builder()
                .token(token)
                .build();
    }
}
