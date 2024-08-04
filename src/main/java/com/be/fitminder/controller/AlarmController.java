package com.be.fitminder.controller;

import com.be.fitminder.domain.Alarm;
import com.be.fitminder.domain.Member;
import com.be.fitminder.dto.AlarmRequestDTO;
import com.be.fitminder.service.AlarmService;
import com.be.fitminder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AlarmController {

    private final AlarmService alarmService;

    /**
     * 알람 생성
     */
    @PostMapping("/members/{memberId}/alarm")
    public ResponseEntity<Long> createAlarm(@PathVariable("memberId") Long memberId, @RequestBody AlarmRequestDTO alarmRequestDTO) throws SchedulerException {
        return ResponseEntity.ok().body(alarmService.createAlarm(alarmRequestDTO, memberId));
    }

    /**
     * 알람 수정
     */
    @PutMapping("/members/{memberId}/alarm")
    public ResponseEntity<String> updateAlarm(@PathVariable("memberId") Long memberId, @RequestBody AlarmRequestDTO alarmRequestDTO) throws SchedulerException {
        alarmService.updateAlarm(alarmRequestDTO, memberId);
        return ResponseEntity.ok().body("알람 수정 완료");
    }

    /**
     * 알람 삭제
     */
    @DeleteMapping("/alarm/{alarmId}")
    public ResponseEntity<String> deleteAlarm(@PathVariable("alarmId") Long alarmId) throws SchedulerException {
        alarmService.deleteAlarm(alarmId);
        return ResponseEntity.ok().body("알람 삭제 완료");
    }
}
