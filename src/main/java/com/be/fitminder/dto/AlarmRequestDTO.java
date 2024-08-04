package com.be.fitminder.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
public class AlarmRequestDTO {

    private String days;

    private LocalTime startTime;

    private LocalTime endTime;

    private int intervalInMinutes;

    public AlarmRequestDTO(String days, LocalTime startTime, LocalTime endTime, int intervalInMinutes) {
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.intervalInMinutes = intervalInMinutes;
    }
}
