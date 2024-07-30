package com.be.fitminder.dto;

import com.be.fitminder.domain.Stretching;
import lombok.Getter;


public class StretchingResponseDTO {


    /**
     * 부위별 스트레칭 조회시 반환 DTO
     */
    @Getter
    public static class PartListDTO {
        private Long id;
        private String name;
        private String part;
        private Long duration;

        public PartListDTO(Stretching stretching){
            this.id = stretching.getId();
            this.name = stretching.getName();
            this.part = stretching.getPart();
            this.duration = stretching.getDuration_time();
        }

    }

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
        private Long duration;

        public StretchingDTO(Stretching stretching){
            this.id = stretching.getId();
            this.name = stretching.getName();
            this.part = stretching.getPart();
            this.effect = stretching.getEffect();
            this.reason = stretching.getReason();
            this.duration = stretching.getDuration_time();
        }
    }

    /**
     * 세부 가이드 내용의 Stretching DTO
     */
    @Getter
    public static class GuideResponseDTO {
        private Long id;
        private String name;
        private Long duration;
        private String guide;

        public GuideResponseDTO(Stretching stretching){
            this.id = stretching.getId();
            this.name = stretching.getName();
            this.duration = stretching.getDuration_time();
            this.guide = stretching.getGuide();
        }
    }

}
