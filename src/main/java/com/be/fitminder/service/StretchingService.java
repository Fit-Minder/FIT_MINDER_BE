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

    public List<StretchingResponseDTO>findStretchingsByPart(String part){
        List<Stretching> stretchings = stretchingRepository.findByPart(part);

        return stretchings.stream()
                .map(stretching -> StretchingResponseDTO.builder()
                        .name(stretching.getName())
                        .part(stretching.getPart()).build())
                .collect(Collectors.toList());
    }

    public StretchingResponseDTO findStretchingById(Long id){
        Stretching stretching = stretchingRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        StretchingResponseDTO stretchingResponseDTO = StretchingResponseDTO.builder()
                .name(stretching.getName())
                .part(stretching.getPart())
                .duration(stretching.getDuration_time())
                .effect(stretching.getEffect())
                .reason(stretching.getReason()).build();
        return stretchingResponseDTO;
    }

    public StretchingResponseDTO findStretchingDetail(Long id){
        Stretching stretching = stretchingRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
        StretchingResponseDTO stretchingResponseDTO = StretchingResponseDTO.builder()
                .guide(stretching.getGuide())
                .name(stretching.getName())
                .duration(stretching.getDuration_time()).build();
        return stretchingResponseDTO;
    }



}
