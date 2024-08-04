package com.be.fitminder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
public class SseController {

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    @GetMapping("/subscribe/{memberId}")
    public SseEmitter subscribe(@PathVariable("memberId") Long memberId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitters.put(memberId, emitter);

        emitter.onCompletion(() -> emitters.remove(memberId));
        emitter.onTimeout(() -> emitters.remove(memberId));
        emitter.onError(e -> emitters.remove(memberId));

        log.info("sse연결완료");
        log.info("emitter" + emitter);

        return emitter;
    }

    public void sendNotification(Long memberId, String message) throws IOException {
        SseEmitter emitter = emitters.get(memberId);

        log.info("emitter : " + emitter);
        if (emitter != null) {
            emitter.send(SseEmitter.event().name("alarm").data(message));
            emitter.complete();
            log.info("알람 이벤트 전송");
        }
    }
}
