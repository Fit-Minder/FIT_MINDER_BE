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
     * 잔디심기
     */
    @PostMapping("/members/{memberId}/grass")
    public ResponseEntity<Long> createGrass(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok().body(grassService.createGrass(memberId));
    }

    @GetMapping("/grasses/{grassId}")
    public ResponseEntity<GrassResponseDTO> getGrass(@PathVariable("grassId") Long grassId) {
        return ResponseEntity.ok().body(grassService.getGrass(grassId));
    }

}
