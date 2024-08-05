package com.be.fitminder.service;

import com.be.fitminder.domain.Member;
import com.be.fitminder.domain.Stretching;
import com.be.fitminder.repository.MemberRepository;
import com.be.fitminder.repository.StretchingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

public class InitService {
    @Component
    @RequiredArgsConstructor
    public class Init {

        private final InitData initData;

        @PostConstruct
        public void init() {
            initData.memberInit();
            initData.stretchingInit();
        }

        @Component
        @RequiredArgsConstructor
        @Transactional
        static class InitData {
            private final MemberRepository memberRepository;

            private final StretchingRepository stretchingRepository;

            public void memberInit() {
                Member testMember = Member.builder()
                        .role("ROLE_USER")
                        .username("TEST")
                        .name("TEST")
                        .build();
                memberRepository.save(testMember);
            }


            public void stretchingInit() {

                Stretching wrist = Stretching.builder()
                        .part("손목")
                        .name("손목 스트레칭")
                        .effect("긴장과 스트레스 완화")
                        .reason("?")
                        .durationTime("1분 25초")
                        .build();
                stretchingRepository.save(wrist);

                Stretching eye = Stretching.builder()
                        .part("눈")
                        .name("눈 스트레칭")
                        .effect("집중력 향상과 두통완화")
                        .reason("?")
                        .durationTime("1분 25초")
                        .build();
                stretchingRepository.save(eye);

                Stretching neck = Stretching.builder()
                        .part("목")
                        .name("목 스트레칭")
                        .effect("긴장과 스트레스 완화")
                        .reason("?")
                        .durationTime("1분 25초")
                        .build();
                stretchingRepository.save(eye);

                Stretching shoulder = Stretching.builder()
                        .part("어꺠")
                        .name("어깨 스트레칭")
                        .effect("바른자세와 스트레스 감소")
                        .reason("?")
                        .durationTime("1분 25초")
                        .build();
                stretchingRepository.save(shoulder);


            }

        }
    }
}
