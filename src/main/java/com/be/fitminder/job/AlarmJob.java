package com.be.fitminder.job;

import com.be.fitminder.controller.SseController;
import com.be.fitminder.dto.fcmDTO.FcmMessageDTO;
import com.be.fitminder.service.MemberService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlarmJob implements Job {

    private final SseController sseController;
    private final MemberService memberService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Long memberId = context.getJobDetail().getJobDataMap().getLong("memberId");
        String message = "Alarm for user: " + memberId + " at " + LocalDateTime.now();
        log.info(message);
        try {
//            sseController.sendNotification(memberId, message);
            sendFCMNotification(memberId, message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void sendFCMNotification(Long memberId, String message) throws FirebaseMessagingException {
        String registrationToken = memberService.getFcmToken(memberId);

        Message fcmMessage = Message.builder()
                .putData("message", message)
                .setToken(registrationToken)
                .build();

        String response = FirebaseMessaging.getInstance().send(fcmMessage);
        log.info("FCM 메시지 전송 완료" + response);
    }


}
