package com.be.fitminder.dto.fcmDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * FCM 전송 DTO
 */
@Getter
@Builder
public class FcmMessageDTO {

    private boolean validateOnly;
    private FcmMessageDTO.Message message;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {
        private FcmMessageDTO.Notification notification;
        private String token;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }
}
