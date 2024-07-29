package com.be.fitminder.service;


import com.be.fitminder.domain.Grass;
import com.be.fitminder.domain.Member;
import com.be.fitminder.dto.GrassResponseDTO;
import com.be.fitminder.repository.GrassRepository;
import com.be.fitminder.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GrassService {

    private final GrassRepository grassRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Long createGrass(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(IllegalArgumentException::new);

        Grass grass = Grass.builder()
                .grassDate(LocalDate.now())
                .member(member)
                .build();

        Grass savedGrass = grassRepository.save(grass);

        return savedGrass.getId();
    }

    public GrassResponseDTO getGrass(Long grassId){
        Grass grass = grassRepository.findById(grassId)
                .orElseThrow(IllegalAccessError::new);

        GrassResponseDTO grassResponseDTO = new GrassResponseDTO(grass);
        return grassResponseDTO;
    }
}
