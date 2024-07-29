package com.be.fitminder.dto;


import com.be.fitminder.domain.Grass;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GrassResponseDTO {

    private Long grassId;
    private LocalDate grassDate;
    private String memberName;


    public GrassResponseDTO(Grass grass){
        this.grassId = grass.getId();
        this.grassDate = grass.getGrassDate();
        this.memberName = grass.getMember().getName();
    }
}
