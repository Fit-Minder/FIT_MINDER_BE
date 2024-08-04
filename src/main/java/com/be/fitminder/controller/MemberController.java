package com.be.fitminder.controller;

import com.be.fitminder.domain.Member;
import com.be.fitminder.dto.MemberDTO;
import com.be.fitminder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberDTO> findAll() {
        List<Member> members = memberService.findAll();
        return members.stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * FCM 토큰 저장
     */
    @PostMapping("/members/{memberId}")
    public ResponseEntity<String> updateFcmToken(@PathVariable("memberId") Long memberId, @RequestParam String token) {
        memberService.updateFcmToken(memberId, token);
        return ResponseEntity.ok("토큰 업데이트 완료");
    }

}

