package com.be.fitminder.dto;

import com.be.fitminder.domain.Stretching;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StretchingResponseDTO {

    private Long id;
    private String name;
    private String part;
    private String effect;
    private String reason;
    private Long duration;
    private String guide;



}
