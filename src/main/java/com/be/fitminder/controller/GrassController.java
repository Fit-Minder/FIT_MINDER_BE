package com.be.fitminder.controller;

import com.be.fitminder.dto.GrassResponseDTO;
import com.be.fitminder.service.GrassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GrassController {

    private final GrassService grassService;

    /**
     * 잔디 심기
     */
    @PostMapping("/members/{memberId}/grass")
    public ResponseEntity<Long> createGrass(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok().body(grassService.createGrass(memberId));
    }

    /**
     * 일주일 잔디 조회
     */
    @GetMapping("/members/{memberId}/week-grass")
    public ResponseEntity<GrassResponseDTO.WeekGrassResponseDTO> findWeekGrass(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok().body(grassService.findWeekGrasses(memberId));
    }

    /**
     * 한달 잔디 조회
     */
    @GetMapping("/members/{memberId}/month-grass")
    public ResponseEntity<GrassResponseDTO.MonthGrassResponseDTO> findMonthGrass(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok().body(grassService.findMonthGrasses(memberId));
    }
}
