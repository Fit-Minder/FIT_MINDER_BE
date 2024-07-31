package com.be.fitminder.controller;

import com.be.fitminder.dto.StretchingResponseDTO;
import com.be.fitminder.service.StretchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StretchingController {

    private final StretchingService stretchingService;

    /**
     * 부위별 스트레칭 조회
     */
    @GetMapping("/stretchings")
    public ResponseEntity<List<StretchingResponseDTO.StretchingDTO>> findStretchingsByPart(@RequestParam String part) {
        return ResponseEntity.ok().body(stretchingService.findStretchingsByPart(part));
    }

    /**
     * 스트레칭 단건 조회
     */
    @GetMapping("/stretchings/{stretchingsId}")
    public ResponseEntity<StretchingResponseDTO.StretchingDTO> findStretching(@PathVariable("stretchingId") Long stretchingId) {
        return ResponseEntity.ok().body(stretchingService.findStretchingById(stretchingId));
    }

    /**
     * 스트레칭 세부 가이드
     */
    @GetMapping("/stretchings/{stretchingId}/guide")
    public ResponseEntity<StretchingResponseDTO.GuideResponseDTO> findStretchingGuideById(@PathVariable("stretchingId") Long stretchingId) {
        return ResponseEntity.ok().body(stretchingService.findStretchingGuideById(stretchingId));
    }
}
