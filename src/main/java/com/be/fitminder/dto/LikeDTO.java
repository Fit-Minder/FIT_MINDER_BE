package com.be.fitminder.dto;

import lombok.Getter;

@Getter
public class LikeDTO {
    private Long memberId;
    private Long stretchingId;

    public LikeDTO(Long memberId, Long stretchingId) {
        this.memberId = memberId;
        this.stretchingId = stretchingId;
    }
}
