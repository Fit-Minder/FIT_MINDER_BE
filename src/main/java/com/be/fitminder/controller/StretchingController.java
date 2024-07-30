package com.be.fitminder.controller;

import com.be.fitminder.domain.Stretching;
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
    public ResponseEntity<List<StretchingResponseDTO>>stretchingListByPart(@RequestParam("part") String part){
        return ResponseEntity.ok().body(stretchingService.findStretchingsByPart(part));
    }

    /**
     * 스트레칭 단건 조회
     */
    @GetMapping("/stretchings/{stretchingId}")
    public ResponseEntity<StretchingResponseDTO>findStretching(@PathVariable("stretchingId") Long id){
        return ResponseEntity.ok().body(stretchingService.findStretchingById(id));

    }


    /**
     * 스트레칭 세부 가이드
     */
    @GetMapping("/stretchings/{stretchingId}/guide")
    public ResponseEntity<StretchingResponseDTO> findStretchingGuide(@PathVariable("stretchingId") Long id){
        return ResponseEntity.ok().body(stretchingService.findStretchingDetail(id));
    }
}
