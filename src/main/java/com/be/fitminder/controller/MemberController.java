package com.be.fitminder.controller;

import com.be.fitminder.domain.Member;
import com.be.fitminder.dto.MemberDTO;
import com.be.fitminder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberDTO> findAll() {
        List<Member> members = memberService.findAll();
        return members.stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
    }
}

