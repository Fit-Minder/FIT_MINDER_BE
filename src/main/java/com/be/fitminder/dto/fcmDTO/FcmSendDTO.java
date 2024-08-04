package com.be.fitminder.dto.fcmDTO;

import lombok.*;


/**
 * 디바이스로부터 전달 받을 객체
 */
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmSendDTO {

    private String token;

    private String title;

    private String body;
}
