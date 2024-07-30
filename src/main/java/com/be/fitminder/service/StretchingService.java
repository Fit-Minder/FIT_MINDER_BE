package com.be.fitminder.service;

import com.be.fitminder.domain.Stretching;
import com.be.fitminder.dto.StretchingResponseDTO;
import com.be.fitminder.repository.StretchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StretchingService {

    private final StretchingRepository stretchingRepository;

    /**
     * 부위별 스트레칭 리스트 반환
     */
    public List<StretchingResponseDTO.PartListDTO> findStretchingsByPart(String part){
        List<Stretching> stretchings = stretchingRepository.findByPart(part);

        return stretchings.stream()
                .map(StretchingResponseDTO.PartListDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * 스트레칭 단건 조회
     */
    public StretchingResponseDTO.StretchingDTO findStretchingById(Long id){
        Stretching stretching = stretchingRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        StretchingResponseDTO.StretchingDTO sResponseDTO = new StretchingResponseDTO.StretchingDTO(stretching);
        return sResponseDTO;
    }

    /**
     * 해당 스트레칭에 대한 세부 가이드
     */
    public StretchingResponseDTO.GuideResponseDTO findStretchingDetail(Long id){
        Stretching stretching = stretchingRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        StretchingResponseDTO.GuideResponseDTO s_ResponseDTO
                = new StretchingResponseDTO.GuideResponseDTO(stretching);

        return s_ResponseDTO;
    }



}
