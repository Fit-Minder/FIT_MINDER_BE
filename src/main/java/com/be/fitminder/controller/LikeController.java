package com.be.fitminder.controller;

import com.be.fitminder.dto.LikeDTO;
import com.be.fitminder.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.be.fitminder.dto.StretchingResponseDTO.StretchingDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LikeController {

    private final LikeService likeService;


    @PostMapping("/members/{member-id}/like/{stretching-id}")
    public ResponseEntity<LikeDTO> addLike(@PathVariable("member-id") Long memberId,
                                           @PathVariable("stretching-id") Long stretchingId) {
        return ResponseEntity.ok().body(likeService.addLike(memberId , stretchingId));
    }

    @GetMapping("/members/{member-id}/like")
    public ResponseEntity<List<StretchingDTO>> getLike(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().body(likeService.getLike(memberId));
    }

    @DeleteMapping("/members/{member-id}/like/{stretching-id}")
    public ResponseEntity<String> deleteLike(@PathVariable("member-id") Long memberId,
                                           @PathVariable("stretching-id") Long stretchingId) {
        likeService.deleteLike(memberId,stretchingId);
        return ResponseEntity.ok().body("삭제 완료");
    }

}
