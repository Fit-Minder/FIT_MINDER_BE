package com.be.fitminder.service;

import com.be.fitminder.domain.CustomOAuth2User;
import com.be.fitminder.domain.Member;
import com.be.fitminder.dto.MemberDTO;
import com.be.fitminder.dto.oauthDTO.GoogleResponse;
import com.be.fitminder.dto.oauthDTO.KakaoResponse;
import com.be.fitminder.dto.oauthDTO.NaverResponse;
import com.be.fitminder.dto.oauthDTO.OAuth2Response;
import com.be.fitminder.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("{}", oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        Optional<Member> existData = memberRepository.findByUsername(username);

        if (existData.isEmpty()) {
            Member member = Member.builder()
                    .username(username)
                    .name(oAuth2Response.getName())
                    .role("ROLE_USER").build();

            memberRepository.save(member);

            MemberDTO memberDTO = MemberDTO.builder()
                    .username(username)
                    .name(oAuth2Response.getName())
                    .role("ROLE_USER")
                    .build();


            return new CustomOAuth2User(memberDTO);
        }

        else {

            existData.get().changeName(oAuth2Response.getName());

            memberRepository.save(existData.get());

            MemberDTO memberDTO = MemberDTO.builder()
                    .username(existData.get().getUsername())
                    .name(oAuth2Response.getName())
                    .role(existData.get().getRole())
                    .build();

            return new CustomOAuth2User(memberDTO);
        }
    }
}