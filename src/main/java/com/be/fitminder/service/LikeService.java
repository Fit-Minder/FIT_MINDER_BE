package com.be.fitminder.service;

import com.be.fitminder.domain.Like;
import com.be.fitminder.domain.Member;
import com.be.fitminder.domain.Stretching;
import com.be.fitminder.dto.LikeDTO;
import com.be.fitminder.repository.LikeRepository;
import com.be.fitminder.repository.MemberRepository;
import com.be.fitminder.repository.StretchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.be.fitminder.dto.StretchingResponseDTO.StretchingDTO;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final StretchingRepository stretchingRepository;

    @Transactional
    public LikeDTO addLike(Long memberId, Long stretchingId) {
        Member member = memberRepository
                .findById(memberId).orElseThrow(() -> new RuntimeException("존재하지 않는 유저"));
        Stretching stretching = stretchingRepository
                .findById(stretchingId).orElseThrow(() -> new RuntimeException("존재하지 않는 스트레칭"));

        likeRepository.save(Like.from(member,stretching));

        return new LikeDTO(memberId,stretchingId);
    }

    public List<StretchingDTO> getLike(Long memberId) {
        Member member = memberRepository
                .findById(memberId).orElseThrow(() -> new RuntimeException("존재하지 않는 유저"));

        List<Like> likes = member.getLikeStretching();

        List<Stretching> likeStretching =
                likes.stream().map(Like::getStretching).toList();

        return likeStretching.stream().map(StretchingDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void deleteLike(Long memberId , Long stretchingId) {
        Member member = memberRepository
                .findById(memberId).orElseThrow(() -> new RuntimeException("존재하지 않는 유저"));

        List<Like> likes = member.getLikeStretching();

        likes.removeIf(like -> like.getStretching().getId().equals(stretchingId));
    }

}
