package com.be.fitminder.dto;

import com.be.fitminder.domain.Member;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String role;
    private String name;
    private String username;

    public MemberDTO(Member member) {
        this.role = member.getRole();
        this.name = member.getName();
        this.username = member.getUsername();
    }
}