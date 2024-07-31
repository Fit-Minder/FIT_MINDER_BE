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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.be.fitminder.dto.GrassResponseDTO.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GrassService {

    private final GrassRepository grassRepository;
    private final MemberRepository memberRepository;

    /**
     * 잔디 심기
     */
    @Transactional
    public Long createGrass(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoSuchElementException::new);

        Grass grass = Grass.builder()
                .grassDate(LocalDate.now())
                .member(member)
                .build();

        Grass savedgrass = grassRepository.save(grass);

        return savedgrass.getId();
    }

    /**
     * 잔디 조회(한달)
     */
    public WeekGrassResponseDTO findWeekGrasses(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoSuchElementException::new);

        LocalDate weekAgo = LocalDate.now().minusWeeks(1);

        List<Grass> weekGrasses = grassRepository.findGrassesByMemberAndGrassDateAfter(member, weekAgo);

        List<GrassDTO> grassDTOS = weekGrasses.stream()
                .map(GrassDTO::new)
                .collect(Collectors.toList());

        return new WeekGrassResponseDTO(member, grassDTOS);
    }

    /**
     * 잔디 조회(일주일)
     */
    public MonthGrassResponseDTO findMonthGrasses(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoSuchElementException::new);

        LocalDate monthAgo = LocalDate.now().minusMonths(1);

        List<Grass> monthGrasses = grassRepository.findGrassesByMemberAndGrassDateAfter(member, monthAgo);

        List<GrassDTO> grassDTOS = monthGrasses.stream()
                .map(GrassDTO::new)
                .collect(Collectors.toList());

        return new MonthGrassResponseDTO(member, grassDTOS);
    }
}
