package com.be.fitminder.dto;

import com.be.fitminder.domain.Grass;
import com.be.fitminder.domain.Member;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
public class GrassResponseDTO {

    /**
     * 잔디 내용만 담는 DTO
     */
    @Getter
    public static class GrassDTO {

        private Long grassId;

        private LocalDate grassDate;

        public GrassDTO(Grass grass) {
            this.grassId = grass.getId();
            this.grassDate = grass.getGrassDate();
        }
    }

    /**
     * 일주일 잔디 반환 DTO
     */
    @Getter
    public static class WeekGrassResponseDTO {

        private String memberName;

        private List<GrassDTO> grassList;



        public WeekGrassResponseDTO(Member member, List<GrassDTO> grassDTOS) {
            this.memberName = member.getName();
            this.grassList = grassDTOS;
        }
    }

    /**
     * 한달 잔디 반환 DTO
     */
    @Getter
    public static class MonthGrassResponseDTO {

        private String memberName;

        private int withTime;

        private int grassCount;

        private List<GrassDTO> grassList;

        public MonthGrassResponseDTO(Member member, List<GrassDTO> grassDTOS) {
            this.memberName = member.getName();
            this.withTime = (int) ChronoUnit.DAYS.between(LocalDate.now(), member.getCreatedAt().toLocalDate());
            this.grassCount = member.getGrasses().size();
            this.grassList = grassDTOS;
        }
    }

}
