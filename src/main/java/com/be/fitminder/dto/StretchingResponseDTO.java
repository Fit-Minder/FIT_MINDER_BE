package com.be.fitminder.dto;

import com.be.fitminder.domain.Stretching;
import lombok.Getter;

public class StretchingResponseDTO {

    /**
     * 스트레칭 단건 조회시 반환 DTO
     */
    @Getter
    public static class StretchingDTO{
        private Long id;
        private String name;
        private String part;
        private String effect;
        private String reason;
        private String durationTime;

        public StretchingDTO(Stretching stretching){
            this.id = stretching.getId();
            this.name = stretching.getName();
            this.part = stretching.getPart();
            this.effect = stretching.getEffect();
            this.reason = stretching.getReason();
            this.durationTime = stretching.getDurationTime();
        }
    }

    /**
     * 세부 가이드 내용의 Stretching DTO
     */
    @Getter
    public static class GuideResponseDTO {
        private Long id;
        private String name;
        private String durationTime;
        private String guide;

        public GuideResponseDTO(Stretching stretching){
            this.id = stretching.getId();
            this.name = stretching.getName();
            this.durationTime = stretching.getDurationTime();
            this.guide = stretching.getGuide();
        }
    }
}
