package com.be.fitminder.controller;

import com.be.fitminder.domain.Alarm;
import com.be.fitminder.domain.Member;
import com.be.fitminder.dto.AlarmRequestDTO;
import com.be.fitminder.service.AlarmService;
import com.be.fitminder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AlarmController {

    private final AlarmService alarmService;

    @PostMapping("/members/{memberId}/alarm")
    public ResponseEntity<Long> createAlarm(@PathVariable("memberId") Long memberId, @RequestBody AlarmRequestDTO alarmRequestDTO) throws SchedulerException {
        return ResponseEntity.ok().body(alarmService.createAlarm(alarmRequestDTO, memberId));
    }

}
