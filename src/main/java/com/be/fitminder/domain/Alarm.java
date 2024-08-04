package com.be.fitminder.domain;

import com.be.fitminder.config.BaseEntity;
import com.be.fitminder.dto.AlarmRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String days;

    private LocalTime startTime;

    private LocalTime endTime;

    private int intervalInMinutes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Alarm of(AlarmRequestDTO alarmRequestDTO, Member member) {
        return Alarm.builder()
                .days(alarmRequestDTO.getDays())
                .startTime(alarmRequestDTO.getStartTime())
                .endTime(alarmRequestDTO.getEndTime())
                .intervalInMinutes(alarmRequestDTO.getIntervalInMinutes())
                .member(member)
                .build();
    }

    public void updateAlarm(AlarmRequestDTO alarmRequestDTO, Member member) {
        this.days = alarmRequestDTO.getDays();
        this.startTime = alarmRequestDTO.getStartTime();
        this.endTime = alarmRequestDTO.getEndTime();
        this.intervalInMinutes = alarmRequestDTO.getIntervalInMinutes();
        this.member = member;
    }
}
