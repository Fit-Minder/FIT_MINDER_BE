package com.be.fitminder.service;

import com.be.fitminder.domain.Stretching;
import com.be.fitminder.dto.StretchingResponseDTO;
import com.be.fitminder.repository.StretchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.be.fitminder.dto.StretchingResponseDTO.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StretchingService {

    private final StretchingRepository stretchingRepository;

    /**
     * 부위별 스트레칭 리스트 반환
     */
    public List<StretchingDTO> findStretchingsByPart(String part) {
        List<Stretching> stretchings = stretchingRepository.findStretchingsByPart(part);

        return stretchings.stream()
                .map(StretchingDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * 스트레칭 단건 조회
     */
    public StretchingDTO findStretchingById(Long stretchingId) {
        Stretching stretching = stretchingRepository.findById(stretchingId)
                .orElseThrow(NoSuchElementException::new);

        return new StretchingDTO(stretching);
    }

    /**
     * 해당 스트레칭에 대한 세부 가이드
     */
    public GuideResponseDTO findStretchingGuideById(Long stretchingId) {
        Stretching stretching = stretchingRepository.findById(stretchingId)
                .orElseThrow(NoSuchElementException::new);

        return new GuideResponseDTO(stretching);
    }

}
